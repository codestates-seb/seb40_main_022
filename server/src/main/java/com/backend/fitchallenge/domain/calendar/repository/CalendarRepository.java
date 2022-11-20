package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.entity.Calendar;
import com.backend.fitchallenge.domain.calendar.util.CalendarId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, CalendarId> {

    Optional<Calendar> findById(CalendarId calendarId);
}
