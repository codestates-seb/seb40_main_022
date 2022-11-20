package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {
//    @Query("SELECT r FROM Record r WHERE r.memberId = :memberId AND r.month = :month")
//    List<Record> findByMemberIdAndMonth(@Param("memberId") Long memberId, @Param("month") int month);
}
