package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.entity.Sports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SportsRepository extends JpaRepository<Sports, Long> {

    @Query("SELECT s FROM Sports s WHERE s.bodyPart = :bodyPart")
    Page<Sports> findByBodyPart(@Param("bodyPart") Sports.BodyPart bodyPart, Pageable pageable);
}
