package com.backend.fitchallenge.global.security.handler;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshToken;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.utils.MemberAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberAuthorityUtils authorityUtils;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * [회원가입 및 로그인 진행]
     * spring security 내부적으로 로그인 완료시 OAuth2User라는 authentication 발급
     * 이걸 이용해서..
     * 1. email 뽑아낸다.
     * 2. db에서 이미 존재하는 이메일이면 로그인 진행
     *    존재하지 않으면 회원가입, 로그인 진행
     * todo.1. password를 뽑아올 것인가. -> oAuth멤버 회원정보 수정시 password입력하게되면??
     * todo.2. OAuth2이메일과 일반 이메일이 같은경우?? - nullable처리해서 하나만 가입하게 해도 되긴 하지만.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        List<String> authorities = authorityUtils.createRoles(email);

        if(!memberAlreadyExist(email)){
            saveMember(email);
        }

        loginAndRedirect(request, response, email, authorities);
    }

    /**
     * 일종의 회원가입 메서드.
     */
    private void saveMember(String email) {
        MemberCreate memberCreate = MemberCreate.builder()
                        .email(email)
                                .build();
        Member member = memberCreate.toMember();

        memberRepository.save(member);
    }

    /**
     * 토큰을 생성하고, 저장하고, 설정한 uri로 응답을 보내는 메서드
     */
    private void loginAndRedirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities) throws IOException {
        String accessToken = delegateAccessToken(username, authorities);
        String refreshToken = delegateRefreshToken(username);

//        Long memberId = findMemberIdByEmail(username); - email로 교체해서 db접근 줄임.
        RefreshToken rtk = RefreshToken.builder()
                .email(username)
                .tokenValue(refreshToken)
                .build();

        refreshTokenRepository.save(rtk);

        String uri = createURI(accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String delegateAccessToken(String username, List<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);

        String subject = username;
        Date expiration = jwtTokenProvider.getTokenExpiration(jwtTokenProvider.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenProvider.encodeBase64SecretKey(jwtTokenProvider.getSecretKey());

        String accessToken = jwtTokenProvider.accessTokenAssembly(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    //todo redis에 저장하기.
    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenProvider.getTokenExpiration(jwtTokenProvider.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenProvider.encodeBase64SecretKey(jwtTokenProvider.getSecretKey());

        String refreshToken = jwtTokenProvider.refreshTokenAssembly(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    /**
     * todo. 추후 수정 고려 - 쿠키에담을까 헤더에 담을까에 대하여
     */
    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .queryParams(queryParams)
                .build()
                .toUri();
    }

    public Long findMemberIdByEmail(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = optionalMember.orElseThrow(()-> new MemberNotExist());

        return member.getId();
    }

    public boolean memberAlreadyExist(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        return optionalMember.isPresent();
    }
}

