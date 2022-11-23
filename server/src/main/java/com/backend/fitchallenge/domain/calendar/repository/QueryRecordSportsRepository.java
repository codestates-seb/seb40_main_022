package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.dto.response.QRecordSportsResponse;
import com.backend.fitchallenge.domain.calendar.dto.response.RecordSportsResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitchallenge.domain.calendar.entity.QRecordSports.recordSports;
import static com.backend.fitchallenge.domain.calendar.entity.QSports.sports;


@Repository
@RequiredArgsConstructor
public class QueryRecordSportsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<RecordSportsResponse> findRecordSportsResponses(Long recordId) {
        return jpaQueryFactory.select(
                new QRecordSportsResponse(
                        sports.id,
                        sports.bodyPart.stringValue(),
                        sports.name,
                        recordSports.set,
                        recordSports.count,
                        recordSports.weight
                        )
                ).from(recordSports).leftJoin(sports).fetchJoin()
                .where(recordSports.record.id.eq(recordId)).fetch();
    }
}
