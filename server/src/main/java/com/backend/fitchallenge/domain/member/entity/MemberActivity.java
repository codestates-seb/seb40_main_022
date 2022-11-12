package com.backend.fitchallenge.domain.member.entity;

import lombok.AccessLevel;
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
}
