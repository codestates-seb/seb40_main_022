package com.backend.fitchallenge.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecordSports is a Querydsl query type for RecordSports
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecordSports extends EntityPathBase<RecordSports> {

    private static final long serialVersionUID = -212726797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecordSports recordSports = new QRecordSports("recordSports");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRecord record;

    public final NumberPath<Integer> set = createNumber("set", Integer.class);

    public final com.backend.fitchallenge.domain.sports.entity.QSports sports;

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QRecordSports(String variable) {
        this(RecordSports.class, forVariable(variable), INITS);
    }

    public QRecordSports(Path<? extends RecordSports> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecordSports(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecordSports(PathMetadata metadata, PathInits inits) {
        this(RecordSports.class, metadata, inits);
    }

    public QRecordSports(Class<? extends RecordSports> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.record = inits.isInitialized("record") ? new QRecord(forProperty("record"), inits.get("record")) : null;
        this.sports = inits.isInitialized("sports") ? new com.backend.fitchallenge.domain.sports.entity.QSports(forProperty("sports")) : null;
    }

}

