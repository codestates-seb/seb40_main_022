package com.backend.fitchallenge.domain.question.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionPicture is a Querydsl query type for QuestionPicture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionPicture extends EntityPathBase<QuestionPicture> {

    private static final long serialVersionUID = -367348576L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionPicture questionPicture = new QQuestionPicture("questionPicture");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath path = createString("path");

    public final QQuestion question;

    public QQuestionPicture(String variable) {
        this(QuestionPicture.class, forVariable(variable), INITS);
    }

    public QQuestionPicture(Path<? extends QuestionPicture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionPicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionPicture(PathMetadata metadata, PathInits inits) {
        this(QuestionPicture.class, metadata, inits);
    }

    public QQuestionPicture(Class<? extends QuestionPicture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

