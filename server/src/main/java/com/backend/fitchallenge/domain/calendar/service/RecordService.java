package com.backend.fitchallenge.domain.calendar.service;

import com.backend.fitchallenge.domain.calendar.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.calendar.dto.response.SimpleSportsResponse;
import com.backend.fitchallenge.domain.calendar.exception.CalendarNotFound;
import com.backend.fitchallenge.domain.calendar.exception.DuplicateRecordCreation;
import com.backend.fitchallenge.domain.calendar.util.CalendarId;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.calendar.dto.request.RecordUpdate;
import com.backend.fitchallenge.domain.calendar.dto.response.RecordResponse;
import com.backend.fitchallenge.domain.calendar.dto.response.SimpleRecordResponse;
import com.backend.fitchallenge.domain.calendar.entity.Record;
import com.backend.fitchallenge.domain.calendar.entity.Sports;
import com.backend.fitchallenge.domain.calendar.exception.NotRecordWriter;
import com.backend.fitchallenge.domain.calendar.exception.RecordNotFound;
import com.backend.fitchallenge.domain.calendar.repository.CalendarRepository;
import com.backend.fitchallenge.domain.calendar.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecordService {

    private final RecordRepository recordRepository;
    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;
    private final SportsService sportsService;

    /**
     * [운동 기록 등록]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.
     * 2. 입력한 날짜가 db 캘린더의 범위에 있는지 확인합니다.
     * 3. memberId와 날짜가 중복된 등록 요청이 아닌지 확인합니다.
     *    (2, 3은 등록 가능한 날짜의 달력만 보여주고, 첫 요청 이후에는 수정만 가능하게 하는 등
     *    프론트엔드 단에서 구조적으로 막을 수 있는 부분이지만 혹시 몰라서 일단 넣어놨습니다.
     *    시험을 하실 때 불편하시다면 해당 부분만 주석처리하셔서 실행해주시길 바라며, 추후 삭제 가능합니다.)
     *
     * 4. record에 입력할 sports의 목록을 생성합니다.
     * 5. record를 생성합니다.
     */
    // todo : 캘린더 존재 여부 확인하는 과정 필요한지 논의 필요
    public Long createRecord(Long memberId, RecordCreate recordCreate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        //2에 해당하는 부분
        if (!calendarRepository.existsById(CalendarId.of(recordCreate))) {
            throw new CalendarNotFound();
        }

        //3에 해당하는 부분
        if (recordRepository.exist(memberId, recordCreate.getStart()))
            throw new DuplicateRecordCreation();

        List<Sports> sports = sportsService.getSports(recordCreate.getSports());

        Record record = Record.createRecord(recordCreate, member.getId(), sports);

        return recordRepository.save(record).getId();
    }

    /**
     * [특정 유저의 월간 운동 기록 조회]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.
     * 2. memberId와 월을 사용해 record가 존재하는지 확인하고, 존재한다면 record의 목록을 반환합니다.
     * 3. record 목록과 각각의 memberResponse를 recordResponse로 변환하고 반환합니다.
     */
    // todo : 챌린지 상대가 있다면 상대의 운동 기록도 조회하는 로직을 추가할 예정입니다.
    // todo : db 접근 효율성 고려해 조회 방법 선택 고려
    @Transactional(readOnly = true)
    public RecordResponse getMonthlyRecordList(Long memberId, int month) {
        log.info("memberId: " + memberId + ", month: " + month);
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);

        List<SimpleRecordResponse> recordResponses = recordRepository.findByMemberIdAndMonth(memberId, month).stream()
                .map(record ->
                        SimpleRecordResponse.builder()
                                .date(LocalDate.of(record.getYear(), record.getMonth(), record.getDay()))
                                .startTime(record.getStartTime())
                                .endTime(record.getEndTime())
                                .sports(record.getRecordSports().stream()
                                        .map(recordSports -> SimpleSportsResponse.toResponse(recordSports.getSports()))
                                        .collect(toList()))
                                .build()
                ).collect(toList());

        return RecordResponse.of(recordResponses, MemberResponse.of(member));
    }

    /**
     * [운동 기록 수정]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.
     * 2. 날짜(연, 월, 일)와 요청을 보낸 memberId를 통해 record가 존재하는지 확인하고, 존재한다면 해당 record를 반환합니다.
     * 3. record에 입력할 sports의 목록을 생성합니다.
     * 4. sports, startTime, endTime 등의 수정사항을 record에 반영합니다.
     */
    // todo : 추후 비즈니스 로직 추가되면 반영
    public Long updateRecord(Long memberId, Long recordId, RecordUpdate recordUpdate) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Record findRecord = recordRepository
                .findById(recordId)
                .orElseThrow(RecordNotFound::new);
        verifyWriter(memberId, findRecord);

        List<Sports> sports = sportsService.getSports(recordUpdate.getSports());

        findRecord.updateRecord(recordUpdate, sports);

        return findRecord.getId();
    }

    /**
     * [운동 기록 삭제]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.
     * 2. recordId를 통해 record가 존재하는지 확인하고 해당 record를 반환합니다.
     * 3. 요청을 보낸 Member가 record의 writer인지 확인합니다.
     * 4. 맞다면, record를 삭제합니다.
     */
    public Long deleteRecord(Long memberId, Long recordId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Record findRecord = findVerifiedRecord(recordId);
        verifyWriter(member.getId(), findRecord);

        recordRepository.delete(findRecord);

        return recordId;
    }

    // record를 조회하고, 존재하면 해당 객체를 반환합니다.
    @Transactional(readOnly = true)
    private Record findVerifiedRecord(Long recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);

        return optionalRecord.orElseThrow(RecordNotFound::new);
    }

    public void verifyRecordExists(RecordCreate recordCreate) {

    }

    // 어떤 member가 record의 writer인지 확인합니다.
    @Transactional(readOnly = true)
    private void verifyWriter(Long memberId, Record findRecord) {

        Long writerId = recordRepository.findMemberIdByRecordId(findRecord.getId());

        if (!writerId.equals(memberId)) {
            throw new NotRecordWriter();
        }
    }
}
