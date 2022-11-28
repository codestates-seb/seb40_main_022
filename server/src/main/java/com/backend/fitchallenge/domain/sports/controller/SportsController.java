package com.backend.fitchallenge.domain.sports.controller;

import com.backend.fitchallenge.domain.sports.service.SportsService;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SportsController {

    private final SportsService sportsService;

    @GetMapping("/sports/detail")
    public ResponseEntity<MultiResponse<?>> listOfBodyPart(@RequestParam String bodyPart,
                                                           @PageableDefault(size=11) Pageable pageable) {
        log.info("bodyPart: " + bodyPart);
        return ResponseEntity.ok(sportsService.getSportsListByPart(bodyPart, pageable));
    }
}
