package com.backend.fitchallenge.domain.calendar.controller;

import com.backend.fitchallenge.domain.calendar.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.calendar.dto.request.RecordUpdate;
import com.backend.fitchallenge.domain.calendar.service.RecordService;
import com.backend.fitchallenge.domain.calendar.util.Month;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    //운동 기록 생성
    @PostMapping("/records")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       @Valid @RequestBody RecordCreate recordCreate) {

        return ResponseEntity.ok(recordService.createRecord(memberDetails.getMemberId(), recordCreate));
    }

    //특정 달의 운동기록 조회
    @GetMapping("/records")
    public ResponseEntity<?> list(@AuthMember MemberDetails memberDetails,
                                  @RequestParam @Month int month) {

        return ResponseEntity.ok(recordService.getMonthlyRecordList(memberDetails.getMemberId(), month));
    }

    //운동 기록 수정
    @PatchMapping("/records/{record-id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("record-id") Long recordId,
                                       @Valid @RequestBody RecordUpdate recordUpdate) {

        return ResponseEntity.ok(recordService.updateRecord(memberDetails.getMemberId(), recordId, recordUpdate));
    }

    //운동 기록 삭제
    @DeleteMapping("/records/{record-id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("record-id") Long recordID) {

        return ResponseEntity.ok(recordService.deleteRecord(memberDetails.getMemberId(), recordID));
    }
}
