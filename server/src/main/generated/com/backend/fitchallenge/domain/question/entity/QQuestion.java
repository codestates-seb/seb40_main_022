package com.backend.fitchallenge.domain.question.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -349333890L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final com.backend.fitchallenge.global.audit.QAuditable _super = new com.backend.fitchallenge.global.audit.QAuditable(this);

    public final ListPath<com.backend.fitchallenge.domain.answer.entity.Answer, com.backend.fitchallenge.domain.answer.entity.QAnswer> answers = this.<com.backend.fitchallenge.domain.answer.entity.Answer, com.backend.fitchallenge.domain.answer.entity.QAnswer>createList("answers", com.backend.fitchallenge.domain.answer.entity.Answer.class, com.backend.fitchallenge.domain.answer.entity.QAnswer.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.fitchallenge.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<QuestionPicture, QQuestionPicture> questionPictures = this.<QuestionPicture, QQuestionPicture>createList("questionPictures", QuestionPicture.class, QQuestionPicture.class, PathInits.DIRECT2);

    public final EnumPath<Question.QuestionTag> questionTag = createEnum("questionTag", Question.QuestionTag.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.fitchallenge.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

