package com.backend.fitchallenge.domain.challenge.dto.request;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitchallenge.domain.challenge.dto.request.QRankingDto is a Querydsl Projection type for RankingDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRankingDto extends ConstructorExpression<RankingDto> {

    private static final long serialVersionUID = 232977111L;

    public QRankingDto(com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> userName, com.querydsl.core.types.Expression<String> profileImage, com.querydsl.core.types.Expression<Integer> height, com.querydsl.core.types.Expression<Integer> weight, com.querydsl.core.types.Expression<Double> point, com.querydsl.core.types.Expression<Integer> period, com.querydsl.core.types.Expression<Long> challengeId) {
        super(RankingDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class, double.class, int.class, long.class}, memberId, userName, profileImage, height, weight, point, period, challengeId);
    }

}

