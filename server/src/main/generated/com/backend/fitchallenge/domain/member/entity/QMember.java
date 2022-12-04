package com.backend.fitchallenge.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1476644826L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.backend.fitchallenge.global.audit.QAuditable _super = new com.backend.fitchallenge.global.audit.QAuditable(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final EnumPath<Authority> authority = createEnum("authority", Authority.class);

    public final com.backend.fitchallenge.domain.challenge.entity.QChallenge challenge;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath job = createString("job");

    public final QMemberActivity memberActivity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath password = createString("password");

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final ListPath<com.backend.fitchallenge.domain.post.entity.Post, com.backend.fitchallenge.domain.post.entity.QPost> posts = this.<com.backend.fitchallenge.domain.post.entity.Post, com.backend.fitchallenge.domain.post.entity.QPost>createList("posts", com.backend.fitchallenge.domain.post.entity.Post.class, com.backend.fitchallenge.domain.post.entity.QPost.class, PathInits.DIRECT2);

    public final QProfileImage profileImage;

    public final ListPath<com.backend.fitchallenge.domain.question.entity.Question, com.backend.fitchallenge.domain.question.entity.QQuestion> questions = this.<com.backend.fitchallenge.domain.question.entity.Question, com.backend.fitchallenge.domain.question.entity.QQuestion>createList("questions", com.backend.fitchallenge.domain.question.entity.Question.class, com.backend.fitchallenge.domain.question.entity.QQuestion.class, PathInits.DIRECT2);

    public final ListPath<com.backend.fitchallenge.domain.record.entity.Record, com.backend.fitchallenge.domain.record.entity.QRecord> records = this.<com.backend.fitchallenge.domain.record.entity.Record, com.backend.fitchallenge.domain.record.entity.QRecord>createList("records", com.backend.fitchallenge.domain.record.entity.Record.class, com.backend.fitchallenge.domain.record.entity.QRecord.class, PathInits.DIRECT2);

    public final NumberPath<Integer> split = createNumber("split", Integer.class);

    public final StringPath username = createString("username");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.challenge = inits.isInitialized("challenge") ? new com.backend.fitchallenge.domain.challenge.entity.QChallenge(forProperty("challenge")) : null;
        this.memberActivity = inits.isInitialized("memberActivity") ? new QMemberActivity(forProperty("memberActivity")) : null;
        this.profileImage = inits.isInitialized("profileImage") ? new QProfileImage(forProperty("profileImage"), inits.get("profileImage")) : null;
    }

}

