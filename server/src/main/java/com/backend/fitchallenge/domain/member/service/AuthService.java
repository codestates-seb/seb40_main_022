package com.backend.fitchallenge.domain.member.service;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.refreshtoken.RefreshToken;
import com.backend.fitchallenge.domain.refreshtoken.RefreshTokenRepository;
import com.backend.fitchallenge.domain.refreshtoken.exception.TokenNotExist;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * [로그아웃]
     * 1. 토큰정보로 rtk DB 조회
     * 2. 삭제
     * @param refreshToken : request에서 추출한 것.
     */
    public void logoutMember(String refreshToken){
        RefreshToken findToken = findExistToken(refreshToken);
        refreshTokenRepository.delete(findToken);
    }

    /**
     * [atk 재발급]
     * 1. rtk로 소유멤버 정보 찾아오기
     * 2. 토큰 생성
     * @param refreshToken : request에서 추출한 것
     * @return 토큰 값
     */
    public String reissueAccessToken(String refreshToken){

        Member tokenOwner = findTokenOwner(refreshToken);
        return jwtTokenProvider.createAccessToken(tokenOwner);
    }


    /**
     * [rtk 재발급]
     * 1. db에 존재하는 토큰인지 확인
     * 2. 소유주 확인
     * 3. 토큰 재생성
     * 4. 토큰 값 업데이트하여 db에 저장
     * 5. 토큰 반환
     * @param refreshToken : request에서 추출한 것
     * @return 토큰 값
     */
    public String reissueRefreshToken(String refreshToken){

        RefreshToken foundRefreshToken = findExistToken(refreshToken);
        Member tokenOwner = findTokenOwner(refreshToken);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(tokenOwner);

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

    // 토큰 값으로 소유주 Id를 찾아온 후 그 값으로 Member 객체 생성 -> 토큰 재발급을 위함
    private Member findTokenOwner(String refreshToken){

        RefreshToken foundRefreshToken = findExistToken(refreshToken);

        return memberRepository.findById(foundRefreshToken.getOwnerId())
                .orElseThrow(()-> new MemberNotExist());
    }
}
