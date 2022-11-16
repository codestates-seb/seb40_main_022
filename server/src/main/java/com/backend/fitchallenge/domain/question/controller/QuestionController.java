package com.backend.fitchallenge.domain.question.controller;

import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdate;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.service.QuestionService;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<Long> create(@Valid @RequestBody QuestionCreate questionCreate) {


        return ResponseEntity.ok(questionService.createQuestion(questionCreate));
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<DetailQuestionResponse> details(@PathVariable Long id) {

        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @GetMapping("/questions")
    public ResponseEntity<MultiResponse<?>> list(@RequestParam int page,
                                                 @RequestParam int size) {

        return ResponseEntity.ok(questionService.getQuestionList(PageRequest.of(page - 1, size)));
    }

    @GetMapping("/questions/search")
    public ResponseEntity<MultiResponse<?>> searchList(@RequestParam int page,
                                                       @RequestParam int size) {
        return null;
    }

    @PatchMapping("/questions/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @Valid @RequestBody QuestionUpdate questionUpdate) {

        return ResponseEntity.ok(questionService.updateQuestion(id, questionUpdate));
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {

        return ResponseEntity.ok(questionService.deleteQuestion(id));
    }
}
