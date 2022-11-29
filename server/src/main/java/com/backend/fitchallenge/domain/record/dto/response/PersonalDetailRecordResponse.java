package com.backend.fitchallenge.domain.record.dto.response;

import com.backend.fitchallenge.domain.record.entity.Record;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
public class PersonalDetailRecordResponse {

    private MemberResponse member;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private List<RecordSportsResponse> sports;

    private String startPicture;

    private String endPicture;

    private String result;

    @Builder
    public PersonalDetailRecordResponse(MemberResponse member,
                                        LocalDate date,
                                        LocalTime startTime,
                                        LocalTime endTime,
                                        List<RecordSportsResponse> sports,
                                        String startPicture,
                                        String endPicture,
                                        String result) {
        this.member = member;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sports = sports;
        this.startPicture = startPicture;
        this.endPicture = endPicture;
        this.result = result;
    }

    public static PersonalDetailRecordResponse of(MemberResponse member, Record record, List<RecordSportsResponse> sports) {
        return PersonalDetailRecordResponse.builder()
                .member(member)
                .date(LocalDate.of(record.getYear(), record.getMonth(), record.getDay()))
                .startTime(record.getStartTime())
                .endTime(record.getEndTime())
                .sports(sports)
                .startPicture(record.getTimePicture().getStartPicPath())
                .endPicture(record.getTimePicture().getEndPicPath())
                .result(record.getResult().toString())
                .build();
    }
}
