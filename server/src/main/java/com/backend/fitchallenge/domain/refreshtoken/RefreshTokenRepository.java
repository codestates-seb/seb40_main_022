package com.backend.fitchallenge.domain.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenValue(String tokenValue);
    void deleteRtkByTokenValue(String tokenValue);
    Optional<RefreshToken> findRefreshTokenByTokenValue(String refreshToken);

}
