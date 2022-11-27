package com.backend.fitchallenge.domain.calendar.repository;

import com.backend.fitchallenge.domain.calendar.util.CalendarId;
import com.backend.fitchallenge.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, CalendarId> {

    Optional<Calendar> findById(CalendarId calendarId);
}
