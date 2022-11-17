package com.backend.fitchallenge.domain.workout.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.MemberResponse;
import com.backend.fitchallenge.domain.workout.entity.Record;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class SimpleRecordResponse {
    private MemberResponse member;

    private int year;

    private int month;

    private int day;

    private LocalTime startTime;

    private LocalTime endTime;

    @Builder
    public SimpleRecordResponse(Record record, MemberResponse memberResponse) {
        this.member = memberResponse;
        this.year = record.getYear();
        this.month = record.getMonth();
        this.day = record.getDay();
        this.startTime = record.getStartTime();
        this.endTime = record.getEndTime();
    }
}
