package com.backend.fitchallenge.domain.workout.controller;

import com.backend.fitchallenge.domain.workout.dto.response.SimpleSportsResponse;
import com.backend.fitchallenge.domain.workout.service.SportsService;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SportsController {

    private final SportsService sportsService;

    /**
     * 전체 운동 조회
     */
    @GetMapping("/sports")
    public ResponseEntity<MultiResponse<?>> list(int page, int size) {

        return ResponseEntity.ok(sportsService.getSportsList(PageRequest.of(page - 1, size)));
    }

    /**
     * 부위별 운동 조회
     */
    @GetMapping("/sports")
    public ResponseEntity<MultiResponse<?>> listOfBodyPart(@RequestParam String bodyPart, int page, int size) {

        return ResponseEntity.ok(sportsService.getSportsListByPart(bodyPart, PageRequest.of(page - 1, size)));
    }
}
