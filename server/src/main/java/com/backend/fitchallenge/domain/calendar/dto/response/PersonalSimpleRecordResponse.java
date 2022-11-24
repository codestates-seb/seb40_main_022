package com.backend.fitchallenge.domain.calendar.dto.response;

import com.backend.fitchallenge.domain.calendar.entity.Record;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;

@Getter
public class PersonalSimpleRecordResponse {

    private Long recordId;

    private LocalDate date;

    private LocalTime timeRecord;

    private int volume;

    private String result;

    @QueryProjection
    public PersonalSimpleRecordResponse(Long recordId,
                                        int year, int month, int day,
                                        LocalTime startTime,
                                        LocalTime endTime,
                                        int volume,
                                        Record.Result result) {
        this.recordId = recordId;
        this.date = LocalDate.of(year, month, day);
        this.timeRecord = endTime.minusSeconds(startTime.toSecondOfDay());
        this.volume = volume;
        this.result = result.getValue();
    }
}
