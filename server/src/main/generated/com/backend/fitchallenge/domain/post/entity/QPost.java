package com.backend.fitchallenge.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1276770446L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.backend.fitchallenge.global.audit.QAuditable _super = new com.backend.fitchallenge.global.audit.QAuditable(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.backend.fitchallenge.domain.like.entity.Likes, com.backend.fitchallenge.domain.like.entity.QLikes> likes = this.<com.backend.fitchallenge.domain.like.entity.Likes, com.backend.fitchallenge.domain.like.entity.QLikes>createList("likes", com.backend.fitchallenge.domain.like.entity.Likes.class, com.backend.fitchallenge.domain.like.entity.QLikes.class, PathInits.DIRECT2);

    public final com.backend.fitchallenge.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<Picture, QPicture> pictures = this.<Picture, QPicture>createList("pictures", Picture.class, QPicture.class, PathInits.DIRECT2);

    public final ListPath<com.backend.fitchallenge.domain.postcomment.entity.PostComment, com.backend.fitchallenge.domain.postcomment.entity.QPostComment> postComments = this.<com.backend.fitchallenge.domain.postcomment.entity.PostComment, com.backend.fitchallenge.domain.postcomment.entity.QPostComment>createList("postComments", com.backend.fitchallenge.domain.postcomment.entity.PostComment.class, com.backend.fitchallenge.domain.postcomment.entity.QPostComment.class, PathInits.DIRECT2);

    public final ListPath<PostTag, QPostTag> postTags = this.<PostTag, QPostTag>createList("postTags", PostTag.class, QPostTag.class, PathInits.DIRECT2);

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.fitchallenge.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

