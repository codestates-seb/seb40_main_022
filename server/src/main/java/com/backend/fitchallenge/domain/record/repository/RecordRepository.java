package com.backend.fitchallenge.domain.record.repository;

import com.backend.fitchallenge.domain.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {
//    @Query("SELECT r FROM Record r WHERE r.memberId = :memberId AND r.month = :month")
//    List<Record> findByMemberIdAndMonth(@Param("memberId") Long memberId, @Param("month") int month);

}
