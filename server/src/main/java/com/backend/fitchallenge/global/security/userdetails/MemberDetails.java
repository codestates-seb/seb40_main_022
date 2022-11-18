package com.backend.fitchallenge.global.security.userdetails;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.security.utils.MemberAuthorityUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 배경지식)
 * Spring security의 authentication의 principal 두가지 종류이다. UserDetails, OAuth2User
 * 이 두 principal중 하나를 security context에 등록했을 때, @AuthenticationPrincipal이 사용 가능한 것이다.
 *
 * 겪었던 문제 -> 일반 로그인은 @AuthMember 사용이 가능하나 소셜 로그인은 사용 불가능 했다.
 *
 * 해결 -> userDetails와 OAuth2User을 이중구현한다.
 */
@Getter
@RequiredArgsConstructor
public class MemberDetails extends Member implements UserDetails, OAuth2User {

    private Long memberId;
    private String email;
    private String password;
    private List<String> roles;
    private Map<String, Object> attributes; // OAuth2의 필드값

    @Builder
    private MemberDetails(Long memberId, String email,
                         String password, List<String> roles,
                         Map<String, Object> attributes) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.attributes = attributes;
    }

    // UserDetails를 구현한 MemberDetails를 만들기 위함 = 일반 로그인을 위해
    public static MemberDetails of(Member member){
        return MemberDetails.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .roles(List.of(member.getAuthority().toString()))
                .build();
    }

    // OAuth2User를 구현한 MemberDetails를 만들기 위함 = 소셜 로그인을 위해
    public static MemberDetails of(Member member, Map<String, Object> attributes){
        return MemberDetails.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .roles(List.of(member.getAuthority().toString()))
                .attributes(attributes)
                .build();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return getEmail();
    } // 여기까지 두가지의 오버라이딩은 OAuth2User의 영향.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.get(0)));
    } // security가 이해하는 권한을 만들기 위함. todo. 필드의 권한을 아예 이 타입으로 받아도 될 것 같다.

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

