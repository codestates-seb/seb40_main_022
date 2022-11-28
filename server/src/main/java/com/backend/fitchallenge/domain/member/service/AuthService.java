package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.notification.repository.EmitterRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshToken;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.domain.refreshtoken.exception.TokenNotExist;
import com.backend.fitchallenge.global.redis.RedisService;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final EmitterRepository emitterRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public void logoutMember(String refreshTokenValue, String accessTokenValue){

        String email = jwtTokenProvider.parseEmail(refreshTokenValue);
        Long untilExpiration = jwtTokenProvider.calExpDuration(accessTokenValue);

        String id = String.valueOf(memberRepository.findByEmail(email).get().getId());

        redisService.deleteValues(email);
        redisService.setBlackListValues(accessTokenValue, "BlackList", untilExpiration);

        refreshTokenRepository.deleteRtkByTokenValue(refreshTokenValue);
        //로그인 멤버와 관련된 SsEmitter, Event모두삭제
        emitterRepository.deleteAllEmitterStartWithId(id);
        emitterRepository.deleteAllEventCacheStartWithId(id);
    }

    public String reissueAccessToken(String refreshTokenValue, String accessTokenValue){

        String email = jwtTokenProvider.parseEmail(refreshTokenValue);
        Long untilExpiration = jwtTokenProvider.calExpDuration(accessTokenValue);

        redisService.setBlackListValues(accessTokenValue, "BlackList", untilExpiration);

        Member tokenOwner = findTokenOwner(email);
        return jwtTokenProvider.createAccessToken(tokenOwner);
    }

    public String reissueRefreshToken(String refreshTokenValue){

        String email = jwtTokenProvider.parseEmail(refreshTokenValue);

        Member tokenOwner = findTokenOwner(email);
        RefreshToken refreshToken = findToken(refreshTokenValue);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(tokenOwner);

        redisService.deleteValues(email);
        redisService.setValues(email,newRefreshToken, jwtTokenProvider.calExpDuration(refreshTokenValue));

        refreshTokenRepository.save(refreshToken.update(newRefreshToken));

        return newRefreshToken;
    }

    private RefreshToken findToken(String refreshTokenValue){
        return refreshTokenRepository.findByTokenValue(refreshTokenValue)
                .orElseThrow(() -> new TokenNotExist());
    }

    private Member findTokenOwner(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberNotExist());
    }
}