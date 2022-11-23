package com.backend.fitchallenge.domain.calendar.dto.response;

import com.backend.fitchallenge.domain.calendar.entity.Sports;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class RecordSportsResponse {

    private Long sportsId;

    private String bodyPart;

    private String name;

    private int set;

    private int count;

    private int weight;

    @QueryProjection
    public RecordSportsResponse(Long sportsId, String bodyPart, String name, int set, int count, int weight) {
        this.sportsId = sportsId;
        this.bodyPart = bodyPart;
        this.name = name;
        this.set = set;
        this.count = count;
        this.weight = weight;
    }
}
