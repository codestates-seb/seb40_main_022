package com.backend.fitchallenge.domain.calendar.controller;

import com.backend.fitchallenge.domain.calendar.service.SportsService;
import com.backend.fitchallenge.global.dto.request.PageRequest;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SportsController {

    private final SportsService sportsService;

    //전체 운동 조회
    @GetMapping("/sports")
    public ResponseEntity<MultiResponse<?>> list(@PageableDefault(size=66) Pageable pageable) {

        return ResponseEntity.ok(sportsService.getSportsList(pageable));
    }

    //부위별 운동 조회
    @GetMapping("/sports/detail")
    public ResponseEntity<MultiResponse<?>> listOfBodyPart(@RequestParam String bodyPart,
                                                           @PageableDefault(size=11) Pageable pageable) {
        log.info("bodyPart: " + bodyPart);
        return ResponseEntity.ok(sportsService.getSportsListByPart(bodyPart, pageable));
    }
}
