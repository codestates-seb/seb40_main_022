package com.backend.fitchallenge.domain.calendar.dto.response;

import com.backend.fitchallenge.domain.calendar.entity.Sports;
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

    public static SportsResponse toResponse(Sports sports) {
        return SportsResponse.builder()
                .sportsId(sports.getId())
                .bodyPart(sports.getBodyPart().getValue())
                .name(sports.getName())
                .build();
    }
}
