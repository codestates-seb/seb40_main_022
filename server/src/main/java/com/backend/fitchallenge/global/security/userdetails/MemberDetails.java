package com.backend.fitchallenge.global.security.userdetails;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.security.utils.MemberAuthorityUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 인증된 멤버의 정보가 담겨있다.
 */
@Getter
@RequiredArgsConstructor
public class MemberDetails extends Member implements UserDetails {

    private Long memberId;
    private String email;
    private String password;
    private List<String> roles;

    MemberDetails(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.roles = List.of(member.getAuthority().toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.get(0)));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

