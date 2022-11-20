package com.backend.fitchallenge.domain.calendar.entity;

import com.backend.fitchallenge.domain.calendar.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.calendar.dto.request.RecordUpdate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID", nullable = false)
    private Long id;

    @Column(name = "CALENDAR_YEAR", nullable = false)
    private int year;

    @Column(name = "CALENDAR_MONTH", nullable = false)
    private int month;

    @Column(name = "CALENDAR_DAY", nullable = false)
    private int day;

    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalTime endTime;

    @Builder.Default
    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecordSports> recordSports = new ArrayList<>();

    public static Record createRecord(RecordCreate recordCreate, Long memberId, List<Sports> sports) {

        Record record = Record.builder()
                .year(recordCreate.getStart().getYear())
                .month(recordCreate.getStart().getMonthValue())
                .day(recordCreate.getStart().getDayOfMonth())
                .startTime(recordCreate.getStartTime())
                .endTime(recordCreate.getEndTime())
                .memberId(memberId)
                .build();

        sports.forEach(sport -> RecordSports.addRecordSports(record, sport));

        return record;
    }

    public void updateRecord(RecordUpdate recordUpdate, List<Sports> sports) {
        LocalTime changedStartTime = recordUpdate.getStartTime();
        LocalTime changedEndTime = recordUpdate.getEndTime();

        this.startTime = changedStartTime != null ? changedStartTime : this.startTime;
        this.endTime = changedEndTime != null ? changedEndTime : this.endTime;
        this.getRecordSports().clear();

        sports.forEach(sport -> RecordSports.addRecordSports(this, sport));
    }
}
