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
    private Double point = 0.0D;

    @Builder
    private MemberActivity(Integer kilogram, Integer dayCount, Double point) {
        this.kilogram = kilogram;
        this.dayCount = dayCount;
        this.point = point;
    }

    public static MemberActivity of(MemberUpdateVO memberUpdateVO){
        return MemberActivity.builder()
                .kilogram(memberUpdateVO.getKilogram())
                .build();
    }

    public void updatePointAndDayCount(Double point, Integer dayCount){

        this.point += point;
        this.dayCount += dayCount;
    }

    public void updatePoint(Double point){
        this.point += point;
    }
}
