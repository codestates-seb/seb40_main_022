package com.backend.fitchallenge.domain.answer.controller;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions/{id}")
@RequiredArgsConstructor
@Slf4j
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/answers")
    public ResponseEntity<Long> create(@PathVariable Long id,
                                       @RequestBody AnswerCreate answerCreate) {
        Long memberId = 0L;

        return ResponseEntity.ok(answerService.createAnswer(memberId, id, answerCreate));
    }

    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @PathVariable("answer-id") Long answerId,
                                       @RequestBody AnswerUpdate answerUpdate) {

        return ResponseEntity.ok(answerService.updateAnswer(id, answerId, answerUpdate));
    }

    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity<Long> delete(@PathVariable("answer-id") Long answerId) {

        Long memberId = 1L;

        return ResponseEntity.ok(answerService.deleteAnswer(memberId, answerId));
    }
}
