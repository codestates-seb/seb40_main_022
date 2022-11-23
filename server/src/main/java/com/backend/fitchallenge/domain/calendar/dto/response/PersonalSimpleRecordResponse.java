package com.backend.fitchallenge.domain.calendar.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;

@Getter
public class PersonalSimpleRecordResponse {

    private Long recordId;

    private LocalDate date;

    private Duration timeRecord;

    private int volume;

    private String result;

    @Builder
    public PersonalSimpleRecordResponse(Long recordId, LocalDate date, Duration timeRecord, int volume, String result) {
        this.recordId = recordId;
        this.date = date;
        this.timeRecord = timeRecord;
        this.volume = volume;
        this.result = result;
    }
}
