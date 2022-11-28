package com.backend.fitchallenge.domain.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChallenge is a Querydsl query type for Challenge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallenge extends EntityPathBase<Challenge> {

    private static final long serialVersionUID = 35272632L;

    public static final QChallenge challenge = new QChallenge("challenge");

    public final com.backend.fitchallenge.global.audit.QAuditable _super = new com.backend.fitchallenge.global.audit.QAuditable(this);

    public final NumberPath<Long> applicantId = createNumber("applicantId", Long.class);

    public final DatePath<java.time.LocalDate> challengeEnd = createDate("challengeEnd", java.time.LocalDate.class);

    public final NumberPath<Integer> challengePoint = createNumber("challengePoint", Integer.class);

    public final DatePath<java.time.LocalDate> challengeStart = createDate("challengeStart", java.time.LocalDate.class);

    public final EnumPath<Challenge.ChallengeStatus> challengeStatus = createEnum("challengeStatus", Challenge.ChallengeStatus.class);

    public final NumberPath<Long> counterpartId = createNumber("counterpartId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QChallenge(String variable) {
        super(Challenge.class, forVariable(variable));
    }

    public QChallenge(Path<? extends Challenge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChallenge(PathMetadata metadata) {
        super(Challenge.class, metadata);
    }

}

