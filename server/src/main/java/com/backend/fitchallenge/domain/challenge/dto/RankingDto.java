package com.backend.fitchallenge.domain.challenge.dto;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

//54p 참조
// QueryDsl 조회용 Projection 생성
@Getter
public class RankingDto {
    private Long memberId;
    private String userName;
    private String profileImage;
    private Integer height;
    private Integer weight;
    private Long point;
    private Integer period;
    private Long challengeId;


    @QueryProjection
    public RankingDto(Long memberId, String userName, String profileImage, Integer height, Integer weight,
                      Long point, Integer period, Long challengeId) {
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
