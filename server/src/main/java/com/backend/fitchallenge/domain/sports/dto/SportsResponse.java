package com.backend.fitchallenge.domain.sports.dto;

import com.backend.fitchallenge.domain.sports.entity.Sports;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SportsResponse {

    private Long sportsId;
    private String bodyPart;
    private String name;

    @Builder
    public SportsResponse(Long sportsId, String bodyPart, String name) {
        this.sportsId = sportsId;
        this.bodyPart = bodyPart;
        this.name = name;
    }

    public static SportsResponse of(Sports sports) {
        return SportsResponse.builder()
                .sportsId(sports.getId())
                .bodyPart(sports.getBodyPart().getValue())
                .name(sports.getName())
                .build();
    }
}
