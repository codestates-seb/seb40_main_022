package com.backend.fitchallenge.domain.config;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class TestMemberDetailService implements UserDetailsService {

    public static final String UserName ="user@example.com";

    private Member getMember() {

        return Member.createBuilder()
                .email(UserName)
                .password("123456")
                .username("하울")
                .build();
    }

    @Override
    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return MemberDetails.of(getMember());
    }

}
