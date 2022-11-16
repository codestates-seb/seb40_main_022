package com.backend.fitchallenge.domain.workout.entity;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.workout.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.workout.dto.request.RecordUpdate;
import com.backend.fitchallenge.domain.workout.util.CalendarId;
import com.backend.fitchallenge.domain.workout.util.RecordId;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(RecordId.class)
public class Record extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "YEAR")
    private int month;

    @Column(name = "YEAR")
    private int day;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @OneToMany(mappedBy = "record")
    private List<Sports> sports = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "year", referencedColumnName = "year"),
            @JoinColumn(name = "month", referencedColumnName = "month"),
            @JoinColumn(name = "day", referencedColumnName = "day")
    })
    private Calendar calendar;

    @Builder
    private Record(int year, int month, int day, LocalTime startTime, LocalTime endTime, Long memberId, List<Sports> sports) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.memberId = memberId;
        this.sports = sports;
    }

    public static Record createRecord(RecordCreate recordCreate, Long memberId, List<Sports> sports) {

        return Record.builder()
                .year(recordCreate.getYear())
                .month(recordCreate.getMonth())
                .day(recordCreate.getDay())
                .startTime(recordCreate.getStartTime())
                .endTime(recordCreate.getEndTime())
                .memberId(memberId)
                .sports(sports)
                .build();
    }

    public void updateRecord(RecordUpdate recordUpdate, List<Sports> sports) {
        LocalTime changedStartTime = recordUpdate.getStartTime();
        LocalTime changedEndTime = recordUpdate.getEndTime();

        this.startTime = changedStartTime != null ? changedStartTime : this.startTime;
        this.endTime = changedEndTime != null ? changedEndTime : this.endTime;
        this.sports = sports;
    }
}
