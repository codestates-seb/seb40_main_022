package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.entity.Record;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepositoryCustom {

    Long findMemberIdByRecordId(Long recordId);

    List<Record> findByMemberIdAndMonth(Long memberId, int month);

    Boolean exist(Long memberId, LocalDate date);
}
