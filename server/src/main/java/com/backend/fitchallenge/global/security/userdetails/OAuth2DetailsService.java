package com.backend.fitchallenge.global.security.userdetails;


import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.global.security.oauth2.GoogleUserInfo;
import com.backend.fitchallenge.global.security.oauth2.KakaoUserInfo;
import com.backend.fitchallenge.global.security.oauth2.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * OAuth2로 부터 가져온 유저 정보로 MemberDetail을 생성하는 클래스
 *
 * 소셜 로그인에서 사용된다. securityConfig 보면 userInfoEndpoint 다음 시작될 서비스에 등록해 두었다.
 */
@RequiredArgsConstructor
@Component
public class OAuth2DetailsService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2UserService delegate = new DefaultOAuth2UserService(); // 클래스 레벨에서 애초에 이걸 상속받아도 된다.

        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth2서버에서 받아온 유저정보를 넣어준다.
        OAuth2UserInfo oAuth2UserInfo = null; // 위에서 받아온 유저정보를 각 기업에 맞게 넣어주기 위해 선언한다. -> if문에서 분기해서 넣어줄 예정

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // registrationId -> 기업을 구분한다.

        if(registrationId.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(registrationId.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();

        if(!memberAlreadyExist(email)){ // 첫 로그인이면 회원가입, 아니면 지나게가 될 것.
            saveMember(email, name);
        }

        Member member = findMember(email);

        return MemberDetails.of(member, oAuth2User.getAttributes()); // 소셜 로그인 principal이 생성되었다.
    }


    private void saveMember(String email, String name) {
        MemberCreate memberCreate = MemberCreate.builder()
                .email(email)
                .username(name)
                .build();
        Member member = memberCreate.toEntity();

        memberRepository.save(member);
    }

    public boolean memberAlreadyExist(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        return optionalMember.isPresent();
    }

    public Member findMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        return optionalMember.orElseThrow(()->new MemberNotExist());
    }
}
