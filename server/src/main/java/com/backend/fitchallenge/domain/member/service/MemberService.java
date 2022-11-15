package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdate;
import com.backend.fitchallenge.domain.member.dto.response.MyPageResponse;
import com.backend.fitchallenge.domain.member.dto.response.DetailsMemberResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractMember;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberExist;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.domain.refreshtoken.exception.TokenNotExist;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * [회원가입]
     * 1. 이미 존재하는 이메일인지 확인한다.
     * 2. 패스워드를 인코딩 하여 member Entity로 변환한다.
     * 3. 저장한다.
     * 4. 멤버 아이디를 리턴한다.
     * @param memberCreate : DTO
     * @return Long : 생성된 멤버의 아이디
     */
    public Long createMember(MemberCreate memberCreate){

        checkAlreadyExist(memberCreate.getEmail());

        Member member = memberCreate.toMember(passwordEncoder);

        memberRepository.save(member);

        return findVerifiedMember(member.getEmail()).getId();
    }

    /**
     * [회원 정보 수정]
     * 1. request에서 로그인 멤버의 이메일을 추출한다.
     * 2. 해당 이메일로 DB에서 멤버정보를 가져온다.
     * 3. Member엔티티의 update 메서드 이용하여 업데이트한다.
     * 4. 저장한다.
     * 5. 필요 정보만 MemberUpdate에 담아서 리턴한다.
     * todo 비밀번호 변경시 로그아웃 처리를 해야할 것인가.
     * @param request - 현재 로그인 멤버의 email을 추출하기 위함
     * @param memberUpdate : DTO
     * @return MemberUpdate : DTO (request, response 형식이 같아서 그대로 리턴)
     */
    public MemberUpdate updateMember(HttpServletRequest request, MemberUpdate memberUpdate){

        String loginMemberEmail = loginMember(request);

        Member findMember = findVerifiedMember(loginMemberEmail);
        findMember.update(memberUpdate, passwordEncoder);

        memberRepository.save(findMember);

        return MemberUpdate.of(findMember);

    }

    /**
     * [마이페이지]
     * 1. 로그인 한 멤버의 이메일 추출
     * 2. 이메일로 정보 찾아옴
     * 3 (todo) 작성 post 목록 불러오기
     * 4. 필요정보만 MyPageResponse에 담는다.
     * @param request - 현재 로그인 멤버의 email을 추출하기 위함
     * @return MyPageResponse
     */
    public MyPageResponse getMyInfo(HttpServletRequest request){

        String loginMemberEmail = loginMember(request);

        Member findMember = findVerifiedMember(loginMemberEmail);

        //post연결해야함
        return MyPageResponse.of(findMember.getUsername(), ExtractActivity.of(findMember.getMemberActivity()));

    }

    /**
     *[특정 유저 정보 조회]
     * @param memberId : 조회하고자 하는 유저의 id
     * @return DetailsMemberResponse
     */
    public DetailsMemberResponse getMember(Long memberId){

        Member findMember = findVerifiedMemberById(memberId);

        //post연결해야함
        return DetailsMemberResponse.of(ExtractMember.of(findMember), ExtractActivity.of(findMember.getMemberActivity()));
    }

    // todo : 인플루언서 랭킹별로 조회하기.
    public void getProfessionalList(){

    }

    /**
     * [회원 정보 삭제]
     * 1. 현재 로그인 한 회원의 이메일 추출
     * 2. 추출한 이메일로 멤버 정보를 조회
     * 3. DB에서 멤버 삭제
     * 4. 로그아웃 처리
     * @param request : 이메일 정보 추출을 위함
     * @return Long : 삭제한 멤버의 id
     */
    public Long deleteMember(HttpServletRequest request){

        String loginMemberEmail = loginMember(request);
        Member findMember = findVerifiedMember(loginMemberEmail);

        Long deletedMemberId = findMember.getId();

        memberRepository.delete(findMember);

        refreshTokenRepository.delete(
                refreshTokenRepository.findById(deletedMemberId).orElseThrow(()->new TokenNotExist()));

        return deletedMemberId;
    }

    /**
     * 아래부터는 기능 메서드
     */
    // 이미 가입된 이메일인지 확인하는 메서드
    public void checkAlreadyExist(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent())
            throw new MemberExist();
    }

    // 이메일로 DB에서 멤버 정보 조회
    public Member findVerifiedMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElseThrow(()->new MemberNotExist());
    }

    // ID값으로 DB에서 멤버 정보 조회
    public Member findVerifiedMemberById(Long memberId){
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        return optionalMember.orElseThrow(() -> new MemberNotExist());
    }

    // request에서 토큰 추출 -> 이메일 추출
    public String loginMember(HttpServletRequest request){
        String refreshToken = request.getHeader("RefreshToken");

        return jwtTokenProvider.getClaims(refreshToken).getBody().getSubject();
    }
}
