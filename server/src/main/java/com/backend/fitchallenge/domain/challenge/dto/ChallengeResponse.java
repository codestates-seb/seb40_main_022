package com.backend.fitchallenge.domain.challenge.dto;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChallengeResponse {

    private Long challengeId;

    private List<String> challengers;

    @Builder
    public ChallengeResponse(Long challengeId, List<String> challengers) {
        this.challengeId = challengeId;
        this.challengers = challengers;
    }

    public static ChallengeResponse toResponse(Long challengeId, List<String> challengers) {
       return ChallengeResponse.builder()
                .challengeId(challengeId)
                .challengers(challengers)
                .build();
    }
}
