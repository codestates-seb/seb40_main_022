package com.backend.fitchallenge.domain.refreshtoken;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @Column(name = "RT_OWNER")
    private Long ownerId;

    @Column(name = "TOKEN_VALUE")
    private String tokenValue;

    @Builder
    public RefreshToken(Long ownerId, String tokenValue) {
        this.ownerId = ownerId;
        this.tokenValue = tokenValue;
    }

    public RefreshToken updateTokenValue(String tokenValue){
        this.tokenValue = tokenValue;
        return this;
    }
}