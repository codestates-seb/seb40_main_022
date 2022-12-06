package com.backend.fitchallenge;

import com.backend.fitchallenge.domain.member.entity.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TestUserDetailsService implements UserDetailsService {

    public static final String UserName = "user@example.com";

    private Member getMember() {

        return Member.createBuilder()
                .email(UserName)
                .username("홍길동")
                .password("1234")
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(UserName)) {

        }
        return null;
    }
}
