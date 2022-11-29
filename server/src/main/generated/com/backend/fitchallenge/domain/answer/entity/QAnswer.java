package com.backend.fitchallenge.domain.answer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 751160174L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer = new QAnswer("answer");

    public final com.backend.fitchallenge.global.audit.QAuditable _super = new com.backend.fitchallenge.global.audit.QAuditable(this);

    public final ListPath<com.backend.fitchallenge.domain.answercomment.entity.AnswerComment, com.backend.fitchallenge.domain.answercomment.entity.QAnswerComment> comments = this.<com.backend.fitchallenge.domain.answercomment.entity.AnswerComment, com.backend.fitchallenge.domain.answercomment.entity.QAnswerComment>createList("comments", com.backend.fitchallenge.domain.answercomment.entity.AnswerComment.class, com.backend.fitchallenge.domain.answercomment.entity.QAnswerComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAccepted = createBoolean("isAccepted");

    public final com.backend.fitchallenge.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.backend.fitchallenge.domain.question.entity.QQuestion question;

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.fitchallenge.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.question = inits.isInitialized("question") ? new com.backend.fitchallenge.domain.question.entity.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

