package com.backend.fitchallenge.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = 738142036L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecord record = new QRecord("record");

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.fitchallenge.domain.member.entity.QMember member;

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final ListPath<RecordSports, QRecordSports> recordSports = this.<RecordSports, QRecordSports>createList("recordSports", RecordSports.class, QRecordSports.class, PathInits.DIRECT2);

    public final EnumPath<Record.Result> result = createEnum("result", Record.Result.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final QTimePicture timePicture;

    public final NumberPath<Integer> volume = createNumber("volume", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QRecord(String variable) {
        this(Record.class, forVariable(variable), INITS);
    }

    public QRecord(Path<? extends Record> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecord(PathMetadata metadata, PathInits inits) {
        this(Record.class, metadata, inits);
    }

    public QRecord(Class<? extends Record> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.backend.fitchallenge.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.timePicture = inits.isInitialized("timePicture") ? new QTimePicture(forProperty("timePicture")) : null;
    }

}

