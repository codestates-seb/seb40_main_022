package com.backend.fitchallenge.domain.answer.controller;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.service.AnswerService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
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
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @RequestBody AnswerCreate answerCreate) {

        return ResponseEntity.ok(answerService.createAnswer(memberDetails.getMemberId(), id, answerCreate));
    }

    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @PathVariable("answer-id") Long answerId,
                                       @RequestBody AnswerUpdate answerUpdate) {

        return ResponseEntity.ok(answerService.updateAnswer(memberDetails.getMemberId(), answerId, answerUpdate));
    }

    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("answer-id") Long answerId) {

        return ResponseEntity.ok(answerService.deleteAnswer(memberDetails.getId(), answerId));
    }

    @PostMapping("/answers/{answer-id}/accept")
    public ResponseEntity<Long> accept(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @PathVariable("answer-id") Long answerId) {

        return ResponseEntity.ok(answerService.accept(memberDetails.getMemberId(), id, answerId));
    }
}
