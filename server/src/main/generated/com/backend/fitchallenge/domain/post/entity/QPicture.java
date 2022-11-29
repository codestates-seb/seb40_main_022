package com.backend.fitchallenge.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPicture is a Querydsl query type for Picture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPicture extends EntityPathBase<Picture> {

    private static final long serialVersionUID = -224418580L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPicture picture = new QPicture("picture");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath path = createString("path");

    public final QPost post;

    public QPicture(String variable) {
        this(Picture.class, forVariable(variable), INITS);
    }

    public QPicture(Path<? extends Picture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPicture(PathMetadata metadata, PathInits inits) {
        this(Picture.class, metadata, inits);
    }

    public QPicture(Class<? extends Picture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

