package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.backend.fitchallenge.domain.member.dto.response.extract.QMemberResponse is a Querydsl Projection type for MemberResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberResponse extends ConstructorExpression<MemberResponse> {

    private static final long serialVersionUID = 541230247L;

    public QMemberResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<String> profileImage) {
        super(MemberResponse.class, new Class<?>[]{long.class, String.class, String.class}, id, username, profileImage);
    }

}

