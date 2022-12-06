package com.backend.fitchallenge.domain.record.entity;

import com.backend.fitchallenge.domain.record.dto.request.RecordCreateVO;
import com.backend.fitchallenge.domain.record.dto.request.RecordUpdateVO;
import com.backend.fitchallenge.domain.sports.entity.Sports;
import com.backend.fitchallenge.domain.sports.dto.SportsRequest;
import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Slf4j
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

    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalTime endTime;

    @Column(name = "VOLUME", nullable = false)
    private int volume;

    @Enumerated(value = EnumType.STRING)
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    // fixme : 연관관계 수정해야 할 수도 있음
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TIME_PICTURE_ID")
    private TimePicture timePicture;

    @Builder.Default
    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecordSports> recordSports = new ArrayList<>();

    public enum Result {
        NO_RESULT("결과 없음"),
        WIN("승리"),
        LOSE("패배");

        private final String value;

        Result(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static Record create(RecordCreateVO recordCreateVO, List<String> imagePathList, Member member, List<Sports> sports) {

        List<SportsRequest> sportsRequests = recordCreateVO.getSports();

        Record record = Record.builder()
                .year(recordCreateVO.getStart().getYear())
                .month(recordCreateVO.getStart().getMonthValue())
                .day(recordCreateVO.getStart().getDayOfMonth())
                .startTime(recordCreateVO.getStartTime())
                .endTime(recordCreateVO.getEndTime())
                .member(member)
                //record에 등록된 sports들의 set, count, weight를 활용해 volume 계산
                .volume(sportsRequests.stream()
                        .map(sportsRequest ->
                                sportsRequest.getSet() *
                                        sportsRequest.getCount() *
                                        sportsRequest.getWeight())
                        .mapToInt(volume -> volume)
                        .sum())
                .result(Result.NO_RESULT)
                .timePicture(TimePicture.of(imagePathList.get(0), imagePathList.get(1)))
                .build();

        //recordSports를 생성하기 위해서는, sportsRequest의 세트, 횟수, 무게에 대한 정보가 필요합니다.
        //각 sports에 해당하는 sportsRequest를 인자로 넘겨주기 위해서 for문으로 처리했습니다.
        //  SportsService의 getSports()를 통해 그 sportsId가 db에 존재하지 않는 요청에 대해 예외를 던지게 하고
        //  sportsRequest의 순서대로 sports를 매핑했기에 List<SportsRequest>와 List<Sports>의 인덱스는 상응할 것입니다.
        for (int i = 0; i < sports.size(); i++) {
            RecordSports.create(record, sports.get(i), sportsRequests.get(i));
        }

        return record;
    }

    public void update(RecordUpdateVO recordUpdateVO, List<String> imagePathList, List<Sports> sports) {
        LocalTime changedStartTime = recordUpdateVO.getStartTime();
        LocalTime changedEndTime = recordUpdateVO.getEndTime();
        List<SportsRequest> changedSportsList = recordUpdateVO.getSports();

        this.startTime = changedStartTime != null ? changedStartTime : this.startTime;
        this.endTime = changedEndTime != null ? changedEndTime : this.endTime;
        this.timePicture =
                // 운동 인증사진을 둘 다 수정하는 경우
                recordUpdateVO.includesBothImages() ?
                        TimePicture.of(imagePathList.get(0), imagePathList.get(1)) :
                // 운동 시작 인증사진을 수정하는 경우
                recordUpdateVO.includesStartImage() ?
                        TimePicture.of(imagePathList.get(0), this.timePicture.getEndPicPath()) :
                // 운동 종료 인증사진을 수정하는 경우
                recordUpdateVO.includesEndImage() ?
                        TimePicture.of(this.timePicture.getStartPicPath(), imagePathList.get(0)) :
                // 사진을 수정하지 않는 경우우
                       this.timePicture;
        this.volume = changedSportsList != null ?
                changedSportsList.stream()
                        .map(sportsRequest ->
                                sportsRequest.getSet() *
                                        sportsRequest.getCount() *
                                        sportsRequest.getWeight())
                        .mapToInt(volume -> volume)
                        .sum() : this.volume;

        this.getRecordSports().clear();

        List<SportsRequest> sportsRequests = recordUpdateVO.getSports();
        log.info("[Record] update() sportsRequests: {}", sportsRequests.toString());
        for (int i = 0; i < sports.size(); i++) {
            RecordSports.create(this, sports.get(i), sportsRequests.get(i));
        }
    }
}
