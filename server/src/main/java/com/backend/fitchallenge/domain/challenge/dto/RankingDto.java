package com.backend.fitchallenge.domain.challenge.dto;

import com.querydsl.core.annotations.QueryProjection;

public class RankingDto {
    private Long memberId;
    private String userName;
    private String profileImage;
    private Integer height;
    private Integer weight;
    private Long point;
    private Integer period;

    @QueryProjection
    public RankingDto(Long memberId, String userName, String profileImage, Integer height, Integer weight,
                      Long point, Integer period) {
        this.memberId = memberId;
        this.userName = userName;
        this.profileImage = profileImage;
        this.height = height;
        this.weight = weight;
        this.point = point;
        this.period = period;
    }
}
