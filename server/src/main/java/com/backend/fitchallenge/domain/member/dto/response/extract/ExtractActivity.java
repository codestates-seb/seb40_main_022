package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.backend.fitchallenge.domain.member.entity.MemberActivity;
import lombok.Builder;
import lombok.Getter;

//마이페이지, 특정 유저정보 조회 response를 위해 필요 정보를 추출하는 클래스
@Getter
public class ExtractActivity{
    private Integer kilogram;
    private Integer dayCount;
    private Double point;
    private Long rank;

    @Builder
    private ExtractActivity(Integer kilogram, Integer dayCount, Double point, Long rank) {
        this.kilogram = kilogram;
        this.dayCount = dayCount;
        this.point = point;
        this.rank = rank;
    }

    public static ExtractActivity of(MemberActivity memberActivity, Long rank){
        return ExtractActivity.builder()
                .kilogram(memberActivity.getKilogram() == null ? '0' : memberActivity.getKilogram())
                .dayCount(memberActivity.getDayCount())
                .point(memberActivity.getPoint())
                .rank(rank)
                .build();
    }
}
