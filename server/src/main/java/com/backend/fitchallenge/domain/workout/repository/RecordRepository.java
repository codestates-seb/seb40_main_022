package com.backend.fitchallenge.domain.workout.repository;

import com.backend.fitchallenge.domain.workout.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {

    @Query("SELECT * FROM Record r WHERE r.year = :year AND r.month = :month AND r.day = :day AND r.memberId = :memberId")
    Optional<Record> findByDateAndMemberId(int year, int month, int day, Long memberId);

    @Query("SELECT r.memberId FROM Record r where r.id = :recordId")
    Long findMemberIdByRecordId(Long recordId);

    @Query("SELECT * from Record r WHERE r.memberId = :memberId AND r.month = :month")
    List<Record> findByMemberIdAndMonth(Long memberId, int month);
}
