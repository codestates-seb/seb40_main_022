package com.backend.fitchallenge.domain.workout.entity;

import com.backend.fitchallenge.domain.workout.util.CalendarId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(CalendarId.class)
public class Calendar {

    @Id
    @Column(name = "YEAR")
    private int year;

    @Id
    @Column(name = "MONTH")
    private int month;

    @Id
    @Column(name = "DAY")
    private int day;

    @OneToMany(mappedBy = "calendar", orphanRemoval = true)
    List<Record> recordList;

    @Builder
    public Calendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
