package com.backend.fitchallenge.global.security.filter;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.global.dto.LoginDto;
import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.exception.MemberAlreadyLoggedIn;
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

/**
 * 설명 : 로그인 인증을 위한 필터.
 *  UsernamePasswordAuthenticationFilter의 역할 :
 *      1) 인증 전 authentication 생성
 *      2) 1을 바탕으로 인증된 authentication 생성 위임 -> attemptAuthentication
 *  이를 JwtAuthenticationFilter가 상속받았기에 위 역할에 더불어
 *      3) 토큰 생성까지 담당 -> successfulAuthentication
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        if(redisService.getValues(loginDto.getUsername()) != null){
            throw new MemberAlreadyLoggedIn();
        }

        // 여기서 생성되는 authentication 은 인증 전. (UsernamePasswordAuthenticationToken 이 authentication 의 구현체)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // 여기서 authenticationManger -> provider -> userDetailsService 까지 다녀오게 됨.(framework 가 해줌)
        return authenticationManager.authenticate(authenticationToken);
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) { // <- 위에서 생성한 인증된 authentication
        Member member = (Member)authResult.getPrincipal(); // <- authentication 의 principal 에 있는 Member

        String accessToken = jwtTokenProvider.createAccessToken(member);
        String refreshToken = jwtTokenProvider.createRefreshToken(member);

        jwtTokenProvider.saveRefreshToken(member.getEmail(), refreshToken); // 리프레시 토큰 저장

        redisService.setValues(member.getEmail(), refreshToken); // 레디스에 저장.

        //todo. 쿠키 이용시 여기서 작성하면 됨. (필요성 중하)

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("RefreshToken", refreshToken);
    }

}