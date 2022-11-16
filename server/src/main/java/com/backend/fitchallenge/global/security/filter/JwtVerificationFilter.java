package com.backend.fitchallenge.global.security.filter;

import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.exception.TokenNotValid;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.utils.MemberAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberAuthorityUtils authorityUtils;
    private final RedisService redisService;

    //todo redis에서 먼저 가져와보기 ---complete---
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
//
            String refreshToken = request.getHeader("refreshToken");
            String email = jwtTokenProvider.parseEmail(refreshToken);

            String accessToken = request.getHeader("Authorization").substring(7);

            if(redisService.getBlackListValues(accessToken) == "BlackList"){
                throw new TokenNotValid(); // accessToken이 만료되거나 로그아웃된 상태라서 (보안을 위해)
            }

            //redis 확인부터. - redis에 존재하지 않으면 db로간다. 그리고 redis에 다시 저장해준다.
            if(redisService.getValues(email) == null){
                jwtTokenProvider.verifiedRefreshToken(refreshToken);
                redisService.setValues(email, refreshToken);
            }

            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);

        } catch (SignatureException se) {
            request.setAttribute("exception", se);
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer");
    }

    // 토큰으로부터 클레임 생성
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");

        Map<String, Object> claims = jwtTokenProvider.getClaims(jws).getBody();

        return claims;
    }

    // 토큰으로부터 뽑아낸 Authentication 객체를 SecurityContext에 저장
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username"); // loginDto에서 설정한 이름
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles")); // security를 위한 권한정보

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
