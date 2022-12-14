package com.backend.fitchallenge.domain.answercomment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswerComment is a Querydsl query type for AnswerComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswerComment extends EntityPathBase<AnswerComment> {

    private static final long serialVersionUID = -1860868776L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswerComment answerComment = new QAnswerComment("answerComment");

    public final com.backend.fitchallenge.global.audit.QAuditable _super = new com.backend.fitchallenge.global.audit.QAuditable(this);

    public final com.backend.fitchallenge.domain.answer.entity.QAnswer answer;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.fitchallenge.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QAnswerComment(String variable) {
        this(AnswerComment.class, forVariable(variable), INITS);
    }

    public QAnswerComment(Path<? extends AnswerComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswerComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswerComment(PathMetadata metadata, PathInits inits) {
        this(AnswerComment.class, metadata, inits);
    }

    public QAnswerComment(Class<? extends AnswerComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new com.backend.fitchallenge.domain.answer.entity.QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.member = inits.isInitialized("member") ? new com.backend.fitchallenge.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

