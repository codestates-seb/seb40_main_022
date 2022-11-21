package com.backend.fitchallenge.domain.question.controller;

import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdate;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.service.QuestionService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.dto.request.PageRequest;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/questions")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       @Valid @RequestBody QuestionCreate questionCreate) {

        List<String> imagePathList = awsS3Service.StoreFile(questionCreate.getFiles());

        return ResponseEntity.ok(questionService.createQuestion(memberDetails.getMemberId(), questionCreate, imagePathList));
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<DetailQuestionResponse> details(@PathVariable Long id) {

        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @GetMapping("/questions")
    public ResponseEntity<MultiResponse<?>> list(@ModelAttribute PageRequest pageable) {

        pageable.setDynamicSort();

        return ResponseEntity.ok(questionService.getQuestionList(pageable));
    }

    @GetMapping("/questions/search")
    public ResponseEntity<MultiResponse<?>> searchList(@ModelAttribute PageRequest pageable, @RequestParam String keyword) {
        pageable.setDynamicSort();

        return ResponseEntity.ok(questionService.getQuestionList(pageable, keyword));
    }

    @PatchMapping("/questions/{id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       @Valid @RequestBody QuestionUpdate questionUpdate) {

        return ResponseEntity.ok(questionService.updateQuestion(memberDetails.getMemberId(), id, questionUpdate));
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id) {

        return ResponseEntity.ok(questionService.deleteQuestion(memberDetails.getMemberId(), id));
    }
}
