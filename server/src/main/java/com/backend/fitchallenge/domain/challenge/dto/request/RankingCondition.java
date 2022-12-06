package com.backend.fitchallenge.domain.challenge.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingCondition {
    //분할, 키, 몸무게, 경력
    private Integer split; 
    private Integer heightGoe;
    private Integer heightLt;
    private Integer weightGoe;
    private Integer weightLt;
    private Integer periodGoe;
    private Integer periodLt;


    public RankingCondition(Integer split, Integer heightGoe, Integer heightLt, Integer weightGoe,
                            Integer weightLt, Integer periodGoe, Integer periodLt) {
        this.split = split;
        this.heightGoe = heightGoe;
        this.heightLt = heightLt;
        this.weightGoe = weightGoe;
        this.weightLt = weightLt;
        this.periodGoe = periodGoe;
        this.periodLt = periodLt;
    }

}
