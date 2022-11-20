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

    //답변 등록
    @PostMapping("/answers")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @RequestBody AnswerCreate answerCreate) {

        return ResponseEntity.ok(answerService.createAnswer(memberDetails.getMemberId(), id, answerCreate));
    }

    //답변 수정
    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @PathVariable("answer-id") Long answerId,
                                       @RequestBody AnswerUpdate answerUpdate) {

        return ResponseEntity.ok(answerService.updateAnswer(memberDetails.getMemberId(), answerId, answerUpdate));
    }

    //답변 삭제
    @DeleteMapping("/answers/{answer-id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("answer-id") Long answerId) {

        return ResponseEntity.ok(answerService.deleteAnswer(memberDetails.getMemberId(), answerId));
    }

    //답변 채택
    @PostMapping("/answers/{answer-id}/accept")
    public ResponseEntity<Long> accept(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @PathVariable("answer-id") Long answerId) {

        return ResponseEntity.ok(answerService.accept(memberDetails.getMemberId(), id, answerId));
    }
}
