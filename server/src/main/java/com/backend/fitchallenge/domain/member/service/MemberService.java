package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberExist;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    public Long createMember(MemberCreate memberCreate){

        checkAlreadyExist(memberCreate.getEmail());
        Member member = memberCreate.toMember(passwordEncoder);
        memberRepository.save(member);
        return findVerifiedMember(member.getEmail()).getId();
    }

    //회원 정보 수정
    public void updateMember(){


    }

    //마이페이지
    public void getMyInfo(){

    }

    //특정 유저 조회
    public void getMembers(){

    }

    //인플루언서 목록 조회
    public void getProfessionalList(){

    }

    //멤버 삭제
    public Long deleteMember(){

        return null;
    }

    /**
     * 아래부터는 기능 메서드
     */
    // unique가 db에 이미 존재하는지 체크
    public void checkAlreadyExist(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent())
            throw new MemberExist();
    }

    // db에 있는 유효한 객체 읽어오기
    public Member findVerifiedMember(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElseThrow(()->new MemberNotExist());
    }
}
