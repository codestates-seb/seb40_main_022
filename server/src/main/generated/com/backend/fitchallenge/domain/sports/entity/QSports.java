package com.backend.fitchallenge.domain.sports.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSports is a Querydsl query type for Sports
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSports extends EntityPathBase<Sports> {

    private static final long serialVersionUID = 471577904L;

    public static final QSports sports = new QSports("sports");

    public final EnumPath<Sports.BodyPart> bodyPart = createEnum("bodyPart", Sports.BodyPart.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.backend.fitchallenge.domain.record.entity.RecordSports, com.backend.fitchallenge.domain.record.entity.QRecordSports> recordSports = this.<com.backend.fitchallenge.domain.record.entity.RecordSports, com.backend.fitchallenge.domain.record.entity.QRecordSports>createList("recordSports", com.backend.fitchallenge.domain.record.entity.RecordSports.class, com.backend.fitchallenge.domain.record.entity.QRecordSports.class, PathInits.DIRECT2);

    public QSports(String variable) {
        super(Sports.class, forVariable(variable));
    }

    public QSports(Path<? extends Sports> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSports(PathMetadata metadata) {
        super(Sports.class, metadata);
    }

}

