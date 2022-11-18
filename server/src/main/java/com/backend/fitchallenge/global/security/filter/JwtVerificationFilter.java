package com.backend.fitchallenge.global.security.filter;

import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.exception.TokenNotValid;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.backend.fitchallenge.global.security.userdetails.MemberDetailsService;
import com.backend.fitchallenge.global.security.utils.MemberAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 설명 : 토큰을 검증하는 곳. (로그인 이후라면 요청마다 들르게 될 곳)
 */
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberAuthorityUtils authorityUtils;
    private final RedisService redisService;
    private final MemberDetailsService memberDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            // http message의 header에서 토큰값 가져오기
            String refreshToken = request.getHeader("refreshToken");
            String accessToken = request.getHeader("Authorization").substring(7);

            // refreshToken에서 email 가져오기.
            String email = jwtTokenProvider.parseEmail(refreshToken);

            // accessToken의 보안성 강화. -> refreshToken은 이미 로그아웃되어서 사라지고, accessToken의 만료시간이 남은 경우 대비.
            if(redisService.getBlackListValues(accessToken) == "BlackList"){
                throw new TokenNotValid(); //
            }

            //redis 확인부터 : Cache Aside 정책. -> redis 먼저 조회하기에, 정보가 존재하면 RDB 접근 하지 않아도 됨.
            if(redisService.getValues(email) == null){
                jwtTokenProvider.verifiedRefreshToken(refreshToken);
                redisService.setValues(email, refreshToken);
            }

            //todo. 인증, 리프레시 토큰 리이슈 관련한 자리로 옮기는 것 염두하기. why? 모든 요청시마다 db를 들르고 시작하기 때문
            //하지만. 이래야 @AuthenticationPrincipal 사용 가능
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

    // 토큰을 검증하고 클레임 생성, jwtTokenProvider의 getclaims에 검증 로직 포함.
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");

        Map<String, Object> claims = jwtTokenProvider.getClaims(jws).getBody();

        return claims;
    }

    // 토큰으로부터 뽑아낸 Authentication 객체를 SecurityContext에 저장
    private void setAuthenticationToContext(Map<String, Object> claims) {

        String username = (String) claims.get("username"); // loginDto에서 설정한 이름
        MemberDetails memberDetails = (MemberDetails) memberDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
