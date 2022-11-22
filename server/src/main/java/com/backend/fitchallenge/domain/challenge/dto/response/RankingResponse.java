package com.backend.fitchallenge.domain.challenge.dto.response;

import com.backend.fitchallenge.domain.challenge.dto.request.RankingDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankingResponse {

    private Long memberId;
    private String userName;
    private String profileImage;
    private Integer height;
    private Integer weight;
    private Long point;
    private Integer period;
    private Boolean challengeStatus;
    private LocalDate challengeEndDate;



    @Builder
    public RankingResponse(Long memberId, String userName, String profileImage, Integer height, Integer weight, Long point,
                           Integer period, Boolean challengeStatus, LocalDate challengeEndDate) {
        this.memberId = memberId;
        this.userName = userName;
        this.profileImage = profileImage;
        this.height = height;
        this.weight = weight;
        this.point = point;
        this.period = period;
        this.challengeStatus = challengeStatus;
        this.challengeEndDate = challengeEndDate;
    }




    public static RankingResponse toResponse(RankingDto rankingDto) {
        return RankingResponse.builder()
                .memberId(rankingDto.getMemberId())
                .userName(rankingDto.getUserName())
                .profileImage(rankingDto.getProfileImage())
                .height(rankingDto.getHeight())
                .weight(rankingDto.getWeight())
                .point(rankingDto.getPoint())
                .period(rankingDto.getPeriod())
                .challengeStatus(false)
                .build();

    }

    public static RankingResponse toResponse(RankingDto rankingDto,LocalDate challengeEnd) {
        return RankingResponse.builder()
                .memberId(rankingDto.getMemberId())
                .userName(rankingDto.getUserName())
                .profileImage(rankingDto.getProfileImage())
                .height(rankingDto.getHeight())
                .weight(rankingDto.getWeight())
                .point(rankingDto.getPoint())
                .period(rankingDto.getPeriod())
                .challengeStatus(true)
                .challengeEndDate(challengeEnd)
                .build();

    }

}
