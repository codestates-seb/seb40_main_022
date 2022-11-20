package com.backend.fitchallenge.global.security.userdetails;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * DB에서 유저 정보를 조회하여 MemberDetail을 생성하는 클래스
 *
 * 일반 로그인에서 사용된다. JwtAuthenticationFilter의 attempt~~ 가 사용하게 될 userDetailsService
 */
@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(()-> new MemberNotExist());
        return MemberDetails.of(findMember);
    }
}
