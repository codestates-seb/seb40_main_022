package com.backend.fitchallenge.domain.record.repository;

import com.backend.fitchallenge.domain.record.dto.response.PersonalSimpleRecordResponse;
import com.backend.fitchallenge.domain.record.entity.Record;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepositoryCustom {

    Long findMemberIdByRecordId(Long recordId);

    Optional<Record> findDailyRecord(Long recordId);

    Optional<Record> findDailyRecordByMemberIdAndDate(Long memberId, Integer year, Integer month, Integer day);

    List<PersonalSimpleRecordResponse> findByMemberIdAndMonth(Long memberId, int month);

    Boolean exist(Long memberId, LocalDate date);

    List<PersonalSimpleRecordResponse> findByMemberIdAndOpponentId(Long memberId, Long opponentId);
}
