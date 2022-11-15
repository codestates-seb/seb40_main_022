package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.backend.fitchallenge.domain.member.entity.MemberActivity;
import lombok.Builder;
import lombok.Getter;

//마이페이지, 특정 유저정보 조회 response를 위해 필요 정보를 추출하는 클래스
@Getter
public class ExtractActivity{
    private Integer kilogram;
    private Integer dayCount;
    private Long point;

    @Builder
    private ExtractActivity(Integer kilogram, Integer dayCount, Long point) {
        this.kilogram = kilogram;
        this.dayCount = dayCount;
        this.point = point;
    }

    public static ExtractActivity of(MemberActivity memberActivity){
        return ExtractActivity.builder()
                .kilogram(memberActivity.getKilogram() == null ? '0' : memberActivity.getKilogram())
                .dayCount(memberActivity.getDayCount())
                .point(memberActivity.getPoint())
                .build();
    }
}
