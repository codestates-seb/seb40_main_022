package com.backend.fitchallenge.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimePicture is a Querydsl query type for TimePicture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimePicture extends EntityPathBase<TimePicture> {

    private static final long serialVersionUID = 1445331022L;

    public static final QTimePicture timePicture = new QTimePicture("timePicture");

    public final StringPath endPicPath = createString("endPicPath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath startPicPath = createString("startPicPath");

    public QTimePicture(String variable) {
        super(TimePicture.class, forVariable(variable));
    }

    public QTimePicture(Path<? extends TimePicture> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimePicture(PathMetadata metadata) {
        super(TimePicture.class, metadata);
    }

}

