package com.backend.fitchallenge.domain.record.entity;

import com.backend.fitchallenge.domain.sports.entity.Sports;
import com.backend.fitchallenge.domain.sports.dto.SportsRequest;
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

    @Column(name = "RECORD_SET", nullable = false)
    private int set;

    @Column(name = "RECORD_COUNT", nullable = false)
    private int count;

    @Column(name = "RECORD_WEIGHT", nullable = false)
    private int weight;

    @Builder
    private RecordSports(Record record, Sports sports, int set, int count, int weight) {
        this.record = record;
        this.sports = sports;
        this.set = set;
        this.count = count;
        this.weight = weight;
    }

    public static void create(Record record, Sports sports, SportsRequest sportsRequest) {
        log.info(sports.toString());
        RecordSports recordSports = RecordSports.builder()
                .record(record)
                .sports(sports)
                .set(sportsRequest.getSet())
                .count(sportsRequest.getCount())
                .weight(sportsRequest.getWeight())
                .build();

        record.getRecordSports().add(recordSports);
    }
}
