package com.backend.fitchallenge.domain.workout.dto.response;

import com.backend.fitchallenge.domain.workout.entity.Sports;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleSportsResponse {

    private Long sportsId;

    private String name;

    @Builder
    private SimpleSportsResponse(Long sportsId, String name) {
        this.sportsId = sportsId;
        this.name = name;
    }

    public static SimpleSportsResponse toResponse(Sports sports) {
        return SimpleSportsResponse.builder()
                .sportsId(sports.getId())
                .name(sports.getName())
                .build();
    }
}
