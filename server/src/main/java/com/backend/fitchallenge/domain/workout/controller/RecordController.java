package com.backend.fitchallenge.domain.workout.controller;

import com.backend.fitchallenge.domain.workout.dto.request.RecordCreate;
import com.backend.fitchallenge.domain.workout.dto.request.RecordUpdate;
import com.backend.fitchallenge.domain.workout.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    /**
     * [운동 기록 생성]
     */
    @PostMapping("/records")
    public ResponseEntity<Long> create(@Valid @RequestBody RecordCreate recordCreate) {

        return ResponseEntity.ok(recordService.createRecord(recordCreate));
    }

    /**
     * [특정 달의 운동기록을 조회]
     *
     * (+) 1-12에 해당하는 숫자가 들어오는지 확인하는 커스텀 애너테이션 추가 예정
     */
    @GetMapping("/records")
    public ResponseEntity<?> list(@Positive @RequestParam int month) {

        return ResponseEntity.ok(recordService.getMonthlyRecordList(month));
    }

    /**
     * [운동 기록 수정]
     */
    @PatchMapping("/records")
    public ResponseEntity<Long> update(@Valid @RequestBody RecordUpdate recordUpdate) {

        return ResponseEntity.ok(recordService.updateRecord(recordUpdate));
    }

    @DeleteMapping("/records/{record-id}")
    public ResponseEntity<Long> delete(@PathVariable("record-id") Long recordID) {

        return ResponseEntity.ok(recordService.deleteRecord(recordID));
    }
}
