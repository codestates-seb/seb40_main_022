package com.backend.fitchallenge.domain.calendar.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendar is a Querydsl query type for Calendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendar extends EntityPathBase<Calendar> {

    private static final long serialVersionUID = -1611832018L;

    public static final QCalendar calendar = new QCalendar("calendar");

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final ListPath<com.backend.fitchallenge.domain.record.entity.Record, com.backend.fitchallenge.domain.record.entity.QRecord> records = this.<com.backend.fitchallenge.domain.record.entity.Record, com.backend.fitchallenge.domain.record.entity.QRecord>createList("records", com.backend.fitchallenge.domain.record.entity.Record.class, com.backend.fitchallenge.domain.record.entity.QRecord.class, PathInits.DIRECT2);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QCalendar(String variable) {
        super(Calendar.class, forVariable(variable));
    }

    public QCalendar(Path<? extends Calendar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCalendar(PathMetadata metadata) {
        super(Calendar.class, metadata);
    }

}

