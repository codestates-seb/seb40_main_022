package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.dto.response.PersonalSimpleRecordResponse;
import com.backend.fitchallenge.domain.calendar.dto.response.QPersonalSimpleRecordResponse;
import com.backend.fitchallenge.domain.calendar.dto.response.RecordSportsResponse;
import com.backend.fitchallenge.domain.calendar.entity.Record;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.QMember;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.backend.fitchallenge.domain.calendar.entity.QRecord.record;
import static com.backend.fitchallenge.domain.calendar.entity.QRecordSports.recordSports;
import static com.backend.fitchallenge.domain.calendar.entity.QSports.sports;
import static com.backend.fitchallenge.domain.challenge.entity.QChallenge.challenge;
import static com.backend.fitchallenge.domain.member.entity.QMember.member;
import static org.springframework.util.ObjectUtils.isEmpty;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RecordRepositoryImpl implements RecordRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long findMemberIdByRecordId(Long recordId) {
        return jpaQueryFactory
                .select(record.member.id)
                .from(record)
                .where(record.id.eq(recordId))
                .fetchOne();
    }

    @Override
    public Optional<Record> findDailyRecord(Long recordId) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(record)
                        .where(record.id.eq(recordId))
                        .fetchOne()
        );
    }

    public Optional<Record> findDailyRecordByMemberIdAndDate(Long memberId,
                                                             Integer year,
                                                             Integer month,
                                                             Integer day) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(record)
                        .from(record)
                        .leftJoin(member).on(record.member.id.eq(member.id))
                        .where(member.id.eq(memberId),
                                yearEq(year),
                                monthEq(month),
                                dayEq(day))
                        .fetchOne()
        );
    }

    @Override
    public List<PersonalSimpleRecordResponse> findByMemberIdAndMonth(Long memberId, int month) {

        return jpaQueryFactory.select(
                new QPersonalSimpleRecordResponse(
                        record.id,
                        record.year,
                        record.month,
                        record.day,
                        record.startTime,
                        record.endTime,
                        record.volume,
                        record.result
                )
                ).from(record)
                .leftJoin(member).on(record.member.id.eq(member.id))
                .where(record.member.id.eq(memberId).and(record.month.eq(month)))
                .fetch();
    }

    @Override
    public List<PersonalSimpleRecordResponse> findByMemberIdAndOpponentId(Long memberId, Long opponentId) {
        return jpaQueryFactory.select(
                new QPersonalSimpleRecordResponse(
                        record.id,
                        record.year,
                        record.month,
                        record.day,
                        record.startTime,
                        record.endTime,
                        record.volume,
                        record.result
                ))
                .leftJoin(member).on(record.member.id.eq(member.id))
                .leftJoin(member.challenge, challenge)
                .where(challengeMemberIdEq(memberId, opponentId),
                        memberIdEq(opponentId))
                .fetch();
    }

    @Override
    public Boolean exist(Long memberId, LocalDate date) {
        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(record)
                .where(memberIdEq(memberId),
                        yearEq(date.getYear()),
                        monthEq(date.getMonthValue()),
                        dayEq(date.getDayOfMonth()))
                .fetchFirst();

        return fetchOne != null;
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? record.member.id.eq(memberId) : null;
    }

    //년 일치
    private BooleanExpression yearEq(Integer year) {
        return year != null ? record.year.eq(year) : null;
    }

    //월 일치
    private BooleanExpression monthEq(Integer month) {
        return month != null ? record.year.eq(month) : null;
    }

    //일 일치
    private BooleanExpression dayEq(Integer day) {
        return day != null ? record.year.eq(day) : null;
    }

    private BooleanExpression challengeMemberIdEq(Long memberId, Long opponentId) {
        return (memberId == null || opponentId == null) ? null : (
                challenge.applicantId.eq(memberId).and(challenge.counterpartId.eq(opponentId))
                        .or(challenge.applicantId.eq(opponentId).and(challenge.counterpartId.eq(memberId))));
    }

}
