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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final EmitterRepository emitterRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    /**
     * [로그아웃]
     * 설명 : redis와 db의 리프레시토큰 정보를 삭제하고, redis에 블랙리스트로 액세스토큰을 등록한다.
     * 1. 리프레시 토큰에서 이메일 정보 추출
     * 2. 액세스 토큰 만료시간 계산 -> redis에 블랙리스트 등록하기 위함(만료시간 후 알아서 사라짐)
     * 3. redis에서 현재 리프레시 토큰 삭제 - 로그아웃 처리
     * 4. redis에 현재 액세스 토큰 블랙리스트 등록
     * 5. DB에서 리프레시 토큰 삭제
     * @param refreshToken : request에서 추출한 것.
     */
    public void logoutMember(String refreshToken, String accessToken){

        String email = jwtTokenProvider.parseEmail(refreshToken);
        Long untilExpiration = jwtTokenProvider.calExpDuration(accessToken);

        String id = String.valueOf(memberRepository.findByEmail(email).get().getId());

        redisService.deleteValues(email);
        redisService.setBlackListValues(accessToken, "BlackList", untilExpiration);

//        RefreshToken findToken = findExistToken(refreshToken);
        refreshTokenRepository.deleteRtkByTokenValue(refreshToken);
        //로그인 멤버와 관련된 SsEmitter, Event모두삭제
        emitterRepository.deleteAllEmitterStartWithId(id);
        emitterRepository.deleteAllEventCacheStartWithId(id);
    }

    /**
     * [atk 재발급]
     * 설명 : 현재의 access토큰은 더이상 사용 불가하도록 블랙리스트에 등록하고 새로운 액세스토큰을 발급한다.
     * 1. 리프레시 토큰에서 이메일 정보 추출
     * 2. 액세스 토큰 만료시간 계산
     * 3. redis에 현재 액세스 토큰 블랙리스트 등록
     * 4. 새로운 액세스토큰 생성 후 리턴
     * @param refreshToken : request에서 추출한 것
     * @return 토큰 값
     */
    public String reissueAccessToken(String refreshToken, String accessToken){

        String email = jwtTokenProvider.parseEmail(refreshToken);
        Long untilExpiration = jwtTokenProvider.calExpDuration(accessToken);

        redisService.setBlackListValues(accessToken, "BlackList", untilExpiration);

        Member tokenOwner = findTokenOwner(email);
        return jwtTokenProvider.createAccessToken(tokenOwner);
    }


    /**
     * [rtk 재발급]
     * 설명 : 리프레시토큰을 재발급하여 redis와 db의 토큰값을 업데이트 한다.
     * 1. 리프레시 토큰에서 이메일 정보 추출
     * 2. email 정보로 소유자의 멤버 정보 불러옴
     * 3. 토큰 값으로 리프레시 토큰 정보 불러옴
     * 4. 리프레시 토큰 생성
     * 5. redis에 기본 정보 삭제, 새로운 정보 등록
     * 6. DB에 리프레시 토큰 정보 업데이트
     * @param refreshToken : request에서 추출한 것
     * @return 토큰 값
     */
    public String reissueRefreshToken(String refreshToken){

        String email = jwtTokenProvider.parseEmail(refreshToken);
        Member tokenOwner = findTokenOwner(email);

        RefreshToken foundRefreshToken = findExistToken(refreshToken);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(tokenOwner);

        redisService.deleteValues(email);
        redisService.setValues(email,newRefreshToken);

        refreshTokenRepository.save(foundRefreshToken.updateTokenValue(newRefreshToken));

        return newRefreshToken;
    }


    // 기능 메서드들

    /**
     * 토큰 값으로 db에서 리프레시토큰 객체정보 불러오기
     */
    private RefreshToken findExistToken(String refreshToken){
        return refreshTokenRepository.findByTokenValue(refreshToken)
                .orElseThrow(() -> new TokenNotExist());
    }

    /**
     * email로 토큰 생성을 위한 멤버 객체정보 불러오기
     */
    private Member findTokenOwner(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberNotExist());
    }
}