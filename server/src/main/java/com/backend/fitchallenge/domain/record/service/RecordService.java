package com.backend.fitchallenge.domain.record.service;

import com.backend.fitchallenge.domain.calendar.exception.CalendarNotFound;
import com.backend.fitchallenge.domain.record.dto.request.RecordCreateVO;
import com.backend.fitchallenge.domain.record.dto.response.*;
import com.backend.fitchallenge.domain.record.exception.*;
import com.backend.fitchallenge.domain.record.repository.QueryRecordSportsRepository;
import com.backend.fitchallenge.domain.calendar.util.CalendarId;

import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.record.dto.request.RecordUpdateVO;
import com.backend.fitchallenge.domain.record.entity.Record;
import com.backend.fitchallenge.domain.sports.entity.Sports;
import com.backend.fitchallenge.domain.calendar.repository.CalendarRepository;
import com.backend.fitchallenge.domain.record.repository.RecordRepository;
import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.sports.service.SportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecordService {

    private final RecordRepository recordRepository;
    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;
    private final QueryRecordSportsRepository recordSportsRepository;
    private final SportsService sportsService;
    private final AwsS3Service awsS3Service;

    public Long createRecord(Long memberId, RecordCreateVO recordCreateVO) {

        if (!calendarRepository.existsById(CalendarId.of(recordCreateVO))) {
            throw new CalendarNotFound();
        }

        if (recordRepository.exist(memberId, recordCreateVO.getStart()))
            throw new DuplicateRecordCreation();

        if (recordCreateVO.getStartTime().isAfter(recordCreateVO.getEndTime())) {
            throw new InvalidTimeInput();
        }

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);

        member.getMemberActivity().updatePointAndDayCount(1.0D, 1);
        memberRepository.save(member);

        List<String> imagePathList =
                List.of(recordCreateVO.getStartImagePath(), recordCreateVO.getEndImagePath());

        log.info("recordCreateVO getSports: {}", recordCreateVO.getSports().toString());
        List<Sports> sportsList = sportsService.getSports(recordCreateVO.getSports());

        Record record = Record.create(recordCreateVO, imagePathList, member, sportsList);

        return recordRepository.save(record).getId();
    }


    public DetailRecordResponse getDailyRecord(Long memberId, Long recordId) {
        Record findRecord = recordRepository.findDailyRecord(recordId).orElseThrow(RecordNotFound::new);

        if (memberId != findRecord.getMember().getId()) {
            throw new NotRecordWriter();
        }

        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        log.info("findMember id: {}", findMember.getId());
        Member opponent = memberRepository.findOpponent(memberId);


        List<RecordSportsResponse> sportsResponses = recordSportsRepository.findRecordSportsResponses(recordId);

        if (opponent == null) {

            return DetailRecordResponse.containingOnly(
                    PersonalDetailRecordResponse.of(MemberResponse.of(findMember), findRecord, sportsResponses));
        }

        else {
            Record opRecord = recordRepository.findDailyRecordByMemberIdAndDate(
                    opponent.getId(),
                    findRecord.getYear(),
                    findRecord.getMonth(),
                    findRecord.getDay()).orElseThrow(RecordNotFound::new);
            List<RecordSportsResponse> opSportsResponses = recordSportsRepository.findRecordSportsResponses(opRecord.getId());

            return DetailRecordResponse.containingBoth(
                    PersonalDetailRecordResponse.of(MemberResponse.of(findMember), findRecord, sportsResponses),
                    PersonalDetailRecordResponse.of(MemberResponse.of(opponent), opRecord, opSportsResponses)
            );
        }
    }


    @Transactional(readOnly = true)
    public SimpleRecordResponse getMonthlyRecordList(Long memberId, int month) {
        log.info("memberId: " + memberId + ", month: " + month);
        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Member opponent = memberRepository.findOpponent(memberId);

        List<PersonalSimpleRecordResponse> recordResponses = recordRepository.findByMemberIdAndMonth(memberId, month);
        log.info("recordResponses: {}", recordResponses.toString());

        if (opponent == null) {

            return SimpleRecordResponse.containingOnly(recordResponses);
        }

        else {
            List<PersonalSimpleRecordResponse> opRecordResponses =
                    recordRepository.findByMemberIdAndOpponentId(findMember.getId(), opponent.getId());

            return SimpleRecordResponse.containingBoth(recordResponses, opRecordResponses);
        }
    }


    public Long updateRecord(Long memberId, Long recordId, RecordUpdateVO recordUpdateVO) {

        Record findRecord = recordRepository
                .findById(recordId)
                .orElseThrow(RecordNotFound::new);
        isWriter(memberId, findRecord);

        //운동 인증사진을 둘 다 수정하는 경우
        List<String> imagePathList = List.of();
        if (recordUpdateVO.includesBothImages()) {
            imagePathList = List.of(recordUpdateVO.getStartImagePath(), recordUpdateVO.getEndImagePath());
        }
        //운동 시작 인증사진만을 수정하는 경우
        else if (recordUpdateVO.includesStartImage()) {
            imagePathList = List.of(recordUpdateVO.getStartImagePath());
        }
        //운동 종료 인증사진만을 수정하는 경우
        else if (recordUpdateVO.includesEndImage()) {
            imagePathList = List.of(recordUpdateVO.getEndImagePath());
        }
        List<Sports> sports = sportsService.getSports(recordUpdateVO.getSports());

        findRecord.update(recordUpdateVO, imagePathList, sports);

        return findRecord.getId();
    }


    public Long deleteRecord(Long memberId, Long recordId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);
        Record findRecord = findRecord(recordId);
        isWriter(member.getId(), findRecord);

        member.getMemberActivity().updatePointAndDayCount(-1.0D,-1);
        memberRepository.save(member);

        List<String> files = findRecord.getTimePicture().toPathList();

        awsS3Service.DeleteFile(files);

        recordRepository.delete(findRecord);

        return recordId;
    }


    @Transactional(readOnly = true)
    public Record findRecord(Long recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);

        return optionalRecord.orElseThrow(RecordNotFound::new);
    }


    @Transactional(readOnly = true)
    public void isWriter(Long memberId, Record findRecord) {

        Long writerId = recordRepository.findMemberIdByRecordId(findRecord.getId());

        if (!writerId.equals(memberId)) {
            throw new NotRecordWriter();
        }
    }
}
