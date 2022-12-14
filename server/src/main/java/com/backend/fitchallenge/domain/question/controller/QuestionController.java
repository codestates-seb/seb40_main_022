package com.backend.fitchallenge.domain.question.controller;

import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.question.dto.request.*;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.service.QuestionService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final AwsS3Service awsS3Service;

    @PostMapping("/questions")
    public ResponseEntity<Long> create(@AuthMember MemberDetails memberDetails,
                                       QuestionCreateVO questionCreateVO) {

        List<String> imagePathList = new ArrayList<>();
        if (!questionCreateVO.getFiles().isEmpty()) {
            imagePathList = awsS3Service.StoreFile(questionCreateVO.getFiles());
            log.info("AwsS3Service StoreFile 동작");
        }

        return ResponseEntity.ok(questionService.createQuestion(memberDetails.getMemberId(), questionCreateVO, imagePathList));
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<DetailQuestionResponse> details(@PathVariable Long id) throws IOException {

        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @GetMapping("/questions")
    public ResponseEntity<MultiResponse<?>> list(PageRequest pageable) {

        return ResponseEntity.ok(questionService.getQuestionList(pageable));
    }

    @GetMapping("/questions/search")
    public ResponseEntity<MultiResponse<?>> searchList(PageRequest pageable,
                                                       @ModelAttribute QuestionSearchQuery questionSearchQuery) {

        QuestionSearch questionSearch = questionSearchQuery.queryParsing();

        return ResponseEntity.ok(questionService.searchQuestionList(pageable, questionSearch));
    }

    @PostMapping("/questions/{id}")
    public ResponseEntity<Long> update(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id,
                                       QuestionUpdateVO questionUpdateVO) throws IOException {

        return ResponseEntity.ok(questionService.updateQuestion(memberDetails.getMemberId(), id, questionUpdateVO));
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Long> delete(@AuthMember MemberDetails memberDetails,
                                       @PathVariable Long id) throws IOException {

        return ResponseEntity.ok(questionService.deleteQuestion(memberDetails.getMemberId(), id));
    }
}
