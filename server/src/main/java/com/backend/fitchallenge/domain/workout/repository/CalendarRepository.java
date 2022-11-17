package com.backend.fitchallenge.domain.workout.repository;

import com.backend.fitchallenge.domain.workout.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query("SELECT COUNT(*) FROM Calendar c WHERE c.year = :year AND c.month = :month")
    Long getCount(int year, int month);
}
