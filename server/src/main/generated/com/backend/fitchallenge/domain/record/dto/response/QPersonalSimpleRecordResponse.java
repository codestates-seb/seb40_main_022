package com.backend.fitchallenge.domain.record.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitchallenge.domain.record.dto.response.QPersonalSimpleRecordResponse is a Querydsl Projection type for PersonalSimpleRecordResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPersonalSimpleRecordResponse extends ConstructorExpression<PersonalSimpleRecordResponse> {

    private static final long serialVersionUID = 1365772474L;

    public QPersonalSimpleRecordResponse(com.querydsl.core.types.Expression<Long> recordId, com.querydsl.core.types.Expression<Integer> year, com.querydsl.core.types.Expression<Integer> month, com.querydsl.core.types.Expression<Integer> day, com.querydsl.core.types.Expression<java.time.LocalTime> startTime, com.querydsl.core.types.Expression<java.time.LocalTime> endTime, com.querydsl.core.types.Expression<Integer> volume, com.querydsl.core.types.Expression<com.backend.fitchallenge.domain.record.entity.Record.Result> result) {
        super(PersonalSimpleRecordResponse.class, new Class<?>[]{long.class, int.class, int.class, int.class, java.time.LocalTime.class, java.time.LocalTime.class, int.class, com.backend.fitchallenge.domain.record.entity.Record.Result.class}, recordId, year, month, day, startTime, endTime, volume, result);
    }

}

