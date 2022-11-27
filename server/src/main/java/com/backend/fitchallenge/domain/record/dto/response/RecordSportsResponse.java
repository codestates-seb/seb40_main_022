package com.backend.fitchallenge.domain.record.dto.response;

import com.backend.fitchallenge.domain.sports.entity.Sports;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class RecordSportsResponse {

    private MemberResponse member;

    private Long sportsId;

    private String bodyPart;

    private String name;

    private int set;

    private int count;

    private int weight;

    @QueryProjection
    public RecordSportsResponse(MemberResponse member, Long sportsId, Sports.BodyPart bodyPart, String name, int set, int count, int weight) {
        this.member = member;
        this.sportsId = sportsId;
        this.bodyPart = bodyPart.getValue();
        this.name = name;
        this.set = set;
        this.count = count;
        this.weight = weight;
    }
}
