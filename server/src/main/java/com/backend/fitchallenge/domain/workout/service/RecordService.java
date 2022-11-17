package com.backend.fitchallenge.domain.workout.service;

import com.backend.fitchallenge.domain.member.dto.response.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.workout.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.workout.dto.request.RecordUpdate;
import com.backend.fitchallenge.domain.workout.dto.request.SportsDto;
import com.backend.fitchallenge.domain.workout.dto.response.RecordResponse;
import com.backend.fitchallenge.domain.workout.dto.response.SimpleRecordResponse;
import com.backend.fitchallenge.domain.workout.entity.Record;
import com.backend.fitchallenge.domain.workout.entity.Sports;
import com.backend.fitchallenge.domain.workout.exception.NotRecordWriter;
import com.backend.fitchallenge.domain.workout.exception.RecordNotFound;
import com.backend.fitchallenge.domain.workout.repository.RecordRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecordService {

    private final RecordRepository recordRepository;
    private final MemberRepository memberRepository;
    private final SportsService sportsService;

    /**
     * [운동 기록 등록]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.(컨트롤러 단의 커스텀 애너테이션으로 대체 예정)
     * 2. (1) record에 입력할 sports의 목록을 생성합니다.
     *    (2) 이 과정에서 새롭게 추가된 운동이 있다면 sportsRepository에 추가합니다.
     * 3. record를 생성합니다.
     */
    public Long createRecord(RecordCreate recordCreate) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        List<Sports> sports = makeSports(recordCreate.getSports());

        Record record = Record.createRecord(recordCreate, member.getId(), sports);

        return recordRepository.save(record).getId();
    }

    /**
     * [특정 유저의 월간 운동 기록 조회]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.(컨트롤러 단의 커스텀 애너테이션으로 대체 예정)
     * 2. memberId와 월을 사용해 record가 존재하는지 확인하고, 존재한다면 record의 목록을 반환합니다.
     * 3. record 목록과 각각의 memberResponse를 recordResponse로 변환하고 반환합니다.
     *
     * (+) 챌린지 상대가 있다면 상대의 운동 기록도 조회하는 로직을 추가할 예정입니다.
     */
    public RecordResponse getMonthlyRecordList(int month) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        List<Record> records = recordRepository.findByMemberIdAndMonth(member.getId(), month);
        if (records == null) {
            throw new RecordNotFound();
        }

        List<SimpleRecordResponse> myRecordResponses = records.stream()
                .map(record -> SimpleRecordResponse.builder()
                        .record(record)
                        .memberResponse(MemberResponse.of(member)
                                ).build()).collect(Collectors.toList());

        return RecordResponse.of(myRecordResponses);
    }

    /**
     * [운동 기록 수정]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.(컨트롤러 단의 커스텀 애너테이션으로 대체 예정)
     * 2. 날짜(연, 월, 일)와 요청을 보낸 memberId를 통해 record가 존재하는지 확인하고, 존재한다면 해당 record를 반환합니다.
     * 3. (1) record에 입력할 sports의 목록을 생성합니다.
     *    (2) 이 과정에서 새롭게 추가된 운동이 있다면 sportsRepository에 추가합니다.
     *      (sports의 등록 방식을 sports controller를 거치는 방법만으로 통일한다면 (2)는 필요하지 않습니다.)
     * 4. sports, startTime, endTime 등의 수정사항을 record에 반영합니다.
     */
    public Long updateRecord(RecordUpdate recordUpdate) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Record findRecord = recordRepository
                .findByDateAndMemberId(recordUpdate.getYear(), recordUpdate.getMonth(), recordUpdate.getDay(), member.getId())
                .orElseThrow(RecordNotFound::new);

        List<Sports> sports = makeSports(recordUpdate.getSports());

        findRecord.updateRecord(recordUpdate, sports);

        return findRecord.getId();
    }

    /**
     * [운동 기록 삭제]
     * 1. 요청을 보낸 member가 존재하는지 확인합니다.(컨트롤러 단의 커스텀 애너테이션으로 대체 예정)
     * 2. recordId를 통해 record가 존재하는지 확인하고 해당 record를 반환합니다.
     * 3. 요청을 보낸 Member가 record의 writer인지 확인합니다.
     * 4. 맞다면, record를 삭제합니다.
     */
    public Long deleteRecord(Long recordId) {

        Member member = memberRepository.findById(1L).orElseThrow(MemberNotExist::new);
        Record findRecord = findVerifiedRecord(recordId);
        verifyWriter(member.getId(), findRecord);

        recordRepository.delete(findRecord);

        return recordId;
    }

    // record를 조회하고, 존재하면 해당 객체를 반환합니다.
    @Transactional(readOnly = true)
    public Record findVerifiedRecord(Long recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);

        return optionalRecord.orElseThrow(RecordNotFound::new);
    }

    // 어떤 member가 record의 writer인지 확인합니다.
    @Transactional(readOnly = true)
    public void verifyWriter(Long memberId, Record findRecord) {

        Long writerId = recordRepository.findMemberIdByRecordId(findRecord.getId());

        if (!writerId.equals(memberId)) {
            throw new NotRecordWriter();
        }
    }

    // sportsDto 목록을 sports의 list로 변환합니다.
    public List<Sports> makeSports(List<SportsDto> sports) {
        return sports.stream().map(sportsService::addSports).collect(Collectors.toList());
    }
}
