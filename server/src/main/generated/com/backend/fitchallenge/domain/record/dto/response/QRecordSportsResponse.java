package com.backend.fitchallenge.domain.record.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitchallenge.domain.record.dto.response.QRecordSportsResponse is a Querydsl Projection type for RecordSportsResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRecordSportsResponse extends ConstructorExpression<RecordSportsResponse> {

    private static final long serialVersionUID = 1317865959L;

    public QRecordSportsResponse(com.querydsl.core.types.Expression<? extends com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse> member, com.querydsl.core.types.Expression<Long> sportsId, com.querydsl.core.types.Expression<com.backend.fitchallenge.domain.sports.entity.Sports.BodyPart> bodyPart, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> set, com.querydsl.core.types.Expression<Integer> count, com.querydsl.core.types.Expression<Integer> weight) {
        super(RecordSportsResponse.class, new Class<?>[]{com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse.class, long.class, com.backend.fitchallenge.domain.sports.entity.Sports.BodyPart.class, String.class, int.class, int.class, int.class}, member, sportsId, bodyPart, name, set, count, weight);
    }

}

