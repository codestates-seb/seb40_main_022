package com.backend.fitchallenge.domain.member.entity;

import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//Embeddable 클래스는 npe를 방지하기위해 디폴트값을 꼭 설정해주어야함.
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberActivity {

    @Column(name = "KILOGRAM")
    private Integer kilogram = 0;

    @Column(name = "DAY_COUNT")
    private Integer dayCount = 0;

    @Column(name = "POINT")
    private Long point = 0L;

    @Column(name = "COMMUNITY_POINT")
    private Long communityPoint = 0L;

    private Integer rank = 0;

    @Builder
    private MemberActivity(Integer kilogram, Integer dayCount, Long point, Integer rank) {
        this.kilogram = kilogram;
        this.dayCount = dayCount;
        this.point = point;
        this.rank = rank;
    }
    public static MemberActivity of(MemberUpdateVO memberUpdateVO){
        return MemberActivity.builder()
                .kilogram(memberUpdateVO.getKilogram())
                .build();
    }
}
