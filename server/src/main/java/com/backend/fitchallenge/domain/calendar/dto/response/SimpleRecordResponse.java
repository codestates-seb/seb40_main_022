package com.backend.fitchallenge.domain.calendar.dto.response;

import com.backend.fitchallenge.domain.calendar.entity.Record;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SimpleRecordResponse {

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private List<SimpleSportsResponse> sports;

    @Builder
    public SimpleRecordResponse(LocalDate date, LocalTime startTime, LocalTime endTime, List<SimpleSportsResponse> sports) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sports = sports;
    }
}
