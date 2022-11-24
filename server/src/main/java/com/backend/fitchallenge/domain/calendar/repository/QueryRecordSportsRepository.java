package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.dto.response.RecordSportsResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitchallenge.domain.calendar.entity.QRecord.record;
import static com.backend.fitchallenge.domain.calendar.entity.QRecordSports.recordSports;
import static com.backend.fitchallenge.domain.calendar.entity.QSports.sports;
import static com.querydsl.core.group.GroupBy.groupBy;


@Repository
@RequiredArgsConstructor
public class QueryRecordSportsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<RecordSportsResponse> findRecordSportsResponses(Long recordId) {
        return jpaQueryFactory.selectFrom(recordSports)
                .join(recordSports.record, record)
                .join(recordSports.sports, sports)
                .where(record.id.eq(recordId))
                .distinct()
                .transform(
                        groupBy(recordSports.id).list(
                                Projections.constructor(RecordSportsResponse.class,
                                        sports.id, sports.bodyPart, sports.name,
                                        recordSports.set, recordSports.count, recordSports.weight)
                        )
                );
    }
}
