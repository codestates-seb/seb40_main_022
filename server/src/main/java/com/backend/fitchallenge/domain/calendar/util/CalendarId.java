package com.backend.fitchallenge.domain.calendar.util;

import com.backend.fitchallenge.domain.calendar.dto.request.RecordCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarId implements Serializable {

    private int year;

    private int month;

    private int day;

    @Builder
    private CalendarId(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static CalendarId of(int year, int month, int day) {
        return CalendarId.builder()
                .year(year)
                .month(month)
                .day(day)
                .build();
    }

    public static CalendarId of(RecordCreate recordCreate) {
        return CalendarId.builder()
                .year(recordCreate.getStart().getYear())
                .month(recordCreate.getStart().getMonthValue())
                .day(recordCreate.getStart().getDayOfMonth())
                .build();
    }
}
