package com.backend.fitchallenge.domain.calendar.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class RecordSports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID")
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPORTS_ID")
    private Sports sports;

    @Builder
    private RecordSports(Record record, Sports sports) {
        this.record = record;
        this.sports = sports;
    }

    public static void addRecordSports(Record record, Sports sports) {
        log.info(sports.toString());
        RecordSports recordSports = RecordSports.builder()
                                                .record(record)
                                                .sports(sports)
                                                .build();

        record.getRecordSports().add(recordSports);
    }
}
