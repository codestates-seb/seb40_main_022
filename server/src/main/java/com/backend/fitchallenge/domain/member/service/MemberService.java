package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    public Long createMember(){

        return null;
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

}
