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

    public void logoutMember(String refreshToken){
        RefreshToken findToken = findExistToken(refreshToken);
        refreshTokenRepository.delete(findToken);
    }

    public String reissueAccessToken(String refreshToken){

        Member tokenOwner = findTokenOwner(refreshToken);
        return jwtTokenProvider.createAccessToken(tokenOwner);
    }


    public String reissueRefreshToken(String refreshToken){

        RefreshToken foundRefreshToken = findExistToken(refreshToken);
        Member tokenOwner = findTokenOwner(refreshToken);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(tokenOwner);

        refreshTokenRepository.save(foundRefreshToken.updateTokenValue(newRefreshToken));

        return newRefreshToken;
    }


    private RefreshToken findExistToken(String refreshToken){
        return refreshTokenRepository.findByTokenValue(refreshToken)
                .orElseThrow(() -> new TokenNotExist());
    }

    private Member findTokenOwner(String refreshToken){

        RefreshToken foundRefreshToken = findExistToken(refreshToken);

        return memberRepository.findById(foundRefreshToken.getOwnerId())
                .orElseThrow(()-> new MemberNotExist());
    }
}
