package com.backend.fitchallenge.global.security.handler;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshToken;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.oauth2.OAuth2UserInfo;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
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
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisService redisService;

    /**
     * [회원가입 및 로그인 진행]
     * spring security 내부적으로 로그인 완료시 OAuth2User라는 authentication 발급
     * 이걸 이용해서..
     * 1. email 뽑아낸다.
     * 2. email로 role을 생성한다.
     *
     * todo.1. OAuth2이메일과 일반 이메일이 같은경우?? - 현재는 unique 정책을 유지 중. -> 일반 이메일과 소셜이메일이 같은 경우는 발생 불가
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal(); // OAuth2DetailsServcie에 의해 authentication이 생성된 상태.

        String email = memberDetails.getEmail();
        List<String> authorities = authorityUtils.createRoles(email);

        loginAndRedirect(request, response, email, authorities);
    }


    /**
     * 토큰을 생성하고, 저장하고, 설정한 uri로 응답을 보내는 메서드
     */
    private void loginAndRedirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities) throws IOException {
        String accessToken = delegateAccessToken(username, authorities);
        String refreshToken = delegateRefreshToken(username);

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


    private String delegateRefreshToken(String username) {

        String subject = username;
        Date expiration = jwtTokenProvider.getTokenExpiration(jwtTokenProvider.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenProvider.encodeBase64SecretKey(jwtTokenProvider.getSecretKey());

        String refreshToken = jwtTokenProvider.refreshTokenAssembly(subject, expiration, base64EncodedSecretKey);

        redisService.setValues(username, refreshToken); // 생성하면서 redis 에 담아주도록 하자.

        return refreshToken;
    }

    /**
     * todo. 추후 수정 고려 - 쿠키에담을까 헤더에 담을까에 대하여
     */
    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token",  accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}

