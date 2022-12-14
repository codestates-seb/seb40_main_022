package com.backend.fitchallenge.domain.challenge.dto.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class RankingDto {

    private Long memberId;
    private String userName;
    private String profileImage;
    private Integer height;
    private Integer weight;
    private Double point;
    private Integer period;
    private Long challengeId;


    @QueryProjection
    public RankingDto(Long memberId, String userName, String profileImage, Integer height, Integer weight,
                      Double point, Integer period, Long challengeId) {
        this.memberId = memberId;
        this.userName = userName;
        this.profileImage = profileImage;
        this.height = height;
        this.weight = weight;
        this.point = point;
        this.period = period;
        this.challengeId = challengeId;
    }
}
