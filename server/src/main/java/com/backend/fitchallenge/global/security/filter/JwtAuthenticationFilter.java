package com.backend.fitchallenge.global.security.filter;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.dto.LoginDto;
import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class); // 역직렬화

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    //todo. redis에 refreshToken 저장하기. ---complete---
    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, // 의문) 어디서 사용되는가
                                            Authentication authResult) {
        Member member = (Member) authResult.getPrincipal();

        String accessToken = jwtTokenProvider.createAccessToken(member);
        String refreshToken = jwtTokenProvider.createRefreshToken(member);

//        Member findMember = jwtTokenProvider.findMember(member.getEmail());
        jwtTokenProvider.saveRefreshToken(member.getEmail(), refreshToken); // 리프레시 토큰 저장

//        Long expiration = jwtTokenProvider.getClaims(refreshToken).getBody().getExpiration().getTime();
        //레디스에 저장.
        redisService.setValues(member.getEmail(), refreshToken);

        //추후 쿠키 이용시 여기서 추가할 것

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("RefreshToken", refreshToken);
    }
}