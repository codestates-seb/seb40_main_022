package com.backend.fitchallenge.domain.workout.repository;

import com.backend.fitchallenge.domain.workout.entity.Sports;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;

public interface SportsRepository extends JpaRepository<Sports, Long> {
    boolean existsByName(String name);

    @Query("SELECT s FROM Sports s WHERE s.bodyPart = :bodyPart")
    Page<Sports> findByBodyPart(Sports.BodyPart bodyPart);
}
