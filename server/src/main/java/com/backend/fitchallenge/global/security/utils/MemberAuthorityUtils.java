package com.backend.fitchallenge.global.security.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberAuthorityUtils {

    @Value("${mail.address.admin}")
    private String adminMailAddress;


    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "PROFESSIONAL", "USER");
    private final List<String> PROFESSIONAL_ROLES_STRING = List.of("PROFESSIONAL", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");


    private final List<GrantedAuthority> ADMIN_ROLES =
            AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_PROFESSIONAL", "ROLE_USER");
    private final List<GrantedAuthority> PROFESSIONAL_ROLES =
            AuthorityUtils.createAuthorityList("ROLE_PROFESSIONAL", "ROLE_USER");
    private final List<GrantedAuthority> USER_ROLES =
            AuthorityUtils.createAuthorityList("ROLE_USER");


    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // (2)
                .collect(Collectors.toList());
        return authorities;
    }

    //추후 프로페셔널 추가예정
    public List<String> createRoles(String email){
        if(email.equals(adminMailAddress))
            return ADMIN_ROLES_STRING;
        return USER_ROLES_STRING;
    }
}
