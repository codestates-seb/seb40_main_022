package com.backend.fitchallenge.domain.calendar.entity;

import com.backend.fitchallenge.domain.calendar.util.CalendarId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(CalendarId.class)
public class Calendar {

    @Id
    @Column(name = "CALENDAR_YEAR")
    private int year;

    @Id
    @Column(name = "CALENDAR_MONTH")
    private int month;

    @Id
    @Column(name = "CALENDAR_DAY")
    private int day;

    @OneToMany(orphanRemoval = true)
    @JoinColumns({
            @JoinColumn(name = "CALENDAR_YEAR", referencedColumnName = "CALENDAR_YEAR"),
            @JoinColumn(name = "CALENDAR_MONTH", referencedColumnName = "CALENDAR_MONTH"),
            @JoinColumn(name = "CALENDAR_DAY", referencedColumnName = "CALENDAR_DAY")
    })
    List<Record> records = new ArrayList<>();

    @Builder
    private Calendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Calendar of(int year, int month, int day) {
        return Calendar.builder()
                .year(year)
                .month(month)
                .day(day)
                .build();
    }
}
