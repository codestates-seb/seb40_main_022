package com.backend.fitchallenge.global.security.jwt;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshToken;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.domain.refreshtoken.exception.TokenNotExist;
import com.backend.fitchallenge.global.security.exception.TokenNotValid;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

//todo. '토큰 자체'와 '토큰으로 진행하는 동작'을 나누는 것이 좋아보임. 현재 코드가 너무 길다.
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Getter
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    //secretKey 암호화
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //secretKey 복호화
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    //만료시간 구하는 메서드
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    public Long calExpDuration(String jws){

        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody().getExpiration();

        Long now = new Date().getTime();

        return expiration.getTime() - now;
    }




    //accessToken 생성방법
    public String accessTokenAssembly(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // principal 에서 정보 뽑아내서 액세스토큰 생성
    public String createAccessToken(Member member) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getAuthority().toString()); // 수정 가능성 존재 11/12

        String subject = member.getEmail();
        Date expiration = getTokenExpiration(accessTokenExpirationMinutes);

        String base64EncodedSecretKey = encodeBase64SecretKey(secretKey);

        String accessToken = accessTokenAssembly(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // refreshToken 생성방법
    public String refreshTokenAssembly(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // principal 에서 정보 뽑아내서 리프레시토큰 생성
    public String createRefreshToken(Member member) {

        String subject = member.getEmail();
        Date expiration = getTokenExpiration(refreshTokenExpirationMinutes);
        String base64EncodedSecretKey = encodeBase64SecretKey(secretKey);

        String refreshToken = refreshTokenAssembly(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    // 토큰 검증 후 claim 반환
    public Jws<Claims> getClaims(String jws) {
        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws);
            return claimsJws;
        }
        catch(JwtException e){
            throw new TokenNotValid();
        }
    }



    // 외부에서 쓸 메서드들

    public String parseEmail(String jws){
        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws);
            return claimsJws.getBody().getSubject();
        }
        catch(JwtException e){
            throw new TokenNotValid();
        }
    }

    //db에 저장.
    public void saveRefreshToken(String email, String refreshToken){
//        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findById(memberId);
//        optionalRefreshToken.ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.save(new RefreshToken(email, refreshToken));
    }

    //db에 토큰 존재하는가 (로그인상태확인)
    public void verifiedRefreshToken(String refreshToken){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByTokenValue(refreshToken);
        if(!optionalRefreshToken.isPresent())
            throw new TokenNotExist();
    }
}
