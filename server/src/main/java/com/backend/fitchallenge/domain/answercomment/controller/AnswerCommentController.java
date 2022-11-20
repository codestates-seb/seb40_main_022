package com.backend.fitchallenge.domain.answercomment.controller;

import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.answercomment.service.AnswerCommentService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("questions/*/answers/{answer-id}")
@RequiredArgsConstructor
@Slf4j
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;

    @PostMapping("/comments")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("answer-id") Long answerId,
                                       @Valid @RequestBody AnswerCommentCreate answerCommentCreate) {

        return ResponseEntity.ok(answerCommentService.createAnswerComment(memberDetails.getMemberId(), answerId, answerCommentCreate));
    }

    @PatchMapping("/comments/{answer-comment-id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("answer-comment-id") Long answerCommentId,
                                       @Valid @RequestBody AnswerCommentUpdate answerCommentUpdate) {

        return ResponseEntity.ok(answerCommentService.updateAnswerComment(memberDetails.getMemberId(), answerCommentId, answerCommentUpdate));
    }

    @DeleteMapping("/comments/{answer-comment-id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable("answer-comment-id") Long answerCommentId) {

        return ResponseEntity.ok(answerCommentService.deleteAnswerComment(memberDetails.getMemberId(), answerCommentId));
    }
}
