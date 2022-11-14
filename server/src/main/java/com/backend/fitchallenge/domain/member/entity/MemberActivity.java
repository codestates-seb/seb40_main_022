package com.backend.fitchallenge.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberActivity {

    @Column(name = "KILOGRAM")
    private Integer kilogram;

    @Column(name = "DAY_COUNT")
    private Integer dayCount;

    @Column(name = "POINT")
    private Long point;

    @Column(name = "COMMUNITY_POINT")
    private Long communityPoint;

    @Builder(builderMethodName = "responseBuilder")
    public MemberActivity(Integer kilogram, Integer dayCount, Long point) {
        this.kilogram = kilogram;
        this.dayCount = dayCount;
        this.point = point;
    }
}
