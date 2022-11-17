package com.backend.fitchallenge.domain.workout.util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordId implements Serializable {

    private int year;

    private int month;

    private int day;

    private Long memberId;

    @Builder
    private RecordId(int year, int month, int day, Long memberId) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.memberId = memberId;
    }

    public static RecordId of(int year, int month, int day, Long memberId) {
        return RecordId.builder()
                .year(year)
                .month(month)
                .day(day)
                .memberId(memberId)
                .build();
    }
}
