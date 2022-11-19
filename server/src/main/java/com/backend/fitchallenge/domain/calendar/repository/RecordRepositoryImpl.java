package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.dto.response.SimpleRecordResponse;
import com.backend.fitchallenge.domain.calendar.entity.Record;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.backend.fitchallenge.domain.calendar.entity.QRecord.record;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RecordRepositoryImpl implements RecordRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long findMemberIdByRecordId(Long recordId) {
        return jpaQueryFactory
                .select(record.memberId)
                .from(record)
                .where(record.id.eq(recordId))
                .fetchOne();
    }

    @Override
    public List<Record> findByMemberIdAndMonth(Long memberId, int month) {
        return jpaQueryFactory
                .select(record)
                .from(record)
                .where(record.memberId.eq(memberId).and(record.month.eq(month)))
                .fetch();
    }

    @Override
    public Boolean exist(Long memberId, LocalDate date) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(record)
                .where(record.memberId.eq(memberId))
                .where(record.year.eq(date.getYear())
                    .and(record.month.eq(date.getMonthValue())
                        .and(record.day.eq(date.getDayOfMonth()))))
                .fetchFirst();

        return fetchOne != null;
    }


}
