package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    /**
     * [로그아웃]
     * 1. 토큰정보로 rtk DB 조회
     * 2. redis에서, db에서 삭제
     * @param refreshToken : request에서 추출한 것.
     * todo . rtk - redis에서 삭제, atk - redis에 블랙리스트 등록.
     */
    public void logoutMember(String refreshToken, String accessToken){
        String email = jwtTokenProvider.parseEmail(refreshToken);

        redisService.deleteValues(email);
        redisService.setBlackListValues(email, accessToken);

//        RefreshToken findToken = findExistToken(refreshToken);
        refreshTokenRepository.deleteRtkByTokenValue(refreshToken);
    }

    /**
     * [atk 재발급]
     * 1. rtk로 소유멤버 정보 찾아오기
     * 2. 토큰 생성
     * @param refreshToken : request에서 추출한 것
     * @return 토큰 값
     */
    public String reissueAccessToken(String refreshToken){
        String email = jwtTokenProvider.parseEmail(refreshToken);

        Member tokenOwner = findTokenOwner(email);
        return jwtTokenProvider.createAccessToken(tokenOwner);
    }


    /**
     * [rtk 재발급]
     * 1. 소유주 확인
     * 2. 토큰 재생성
     * 3. 토큰 값 업데이트하여 db에 저장
     * 4. 토큰 반환
     * //todo : redis에서 정보 바꾸기.
     * @param refreshToken : request에서 추출한 것
     * @return 토큰 값
     */
    public String reissueRefreshToken(String refreshToken){
        String email = jwtTokenProvider.parseEmail(refreshToken);

        RefreshToken foundRefreshToken = findExistToken(refreshToken);
        Member tokenOwner = findTokenOwner(email);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(tokenOwner);

        redisService.deleteValues(email);
        redisService.setValues(email,newRefreshToken);

        refreshTokenRepository.save(foundRefreshToken.updateTokenValue(newRefreshToken));

        return newRefreshToken;
    }


    /**
     * 여기부턴 기능 메서드
     */

    // DB에서 refreshToken 조회
    private RefreshToken findExistToken(String refreshToken){
        return refreshTokenRepository.findByTokenValue(refreshToken)
                .orElseThrow(() -> new TokenNotExist());
    }

    // email로 토큰 생성을 위한 멤버 정보 찾기.
    private Member findTokenOwner(String email){
//        RefreshToken foundRefreshToken = findExistToken(refreshToken);

        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberNotExist());
    }
}