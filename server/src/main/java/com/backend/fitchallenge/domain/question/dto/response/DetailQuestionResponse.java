package com.backend.fitchallenge.domain.question.dto.response;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
import com.backend.fitchallenge.domain.member.dto.MemberResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DetailQuestionResponse {

    private String title;

    private String content;

    private Long view;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse memberResponse;

    private List<AnswerResponse> answers;

    @Builder
    private DetailQuestionResponse(String title, String content, Long view, LocalDateTime createdAt, LocalDateTime modifiedAt, MemberResponse memberResponse, List<AnswerResponse> answers) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.memberResponse = memberResponse;
        this.answers = answers;
    }

    public static DetailQuestionResponse of(Question question, MemberResponse memberResponse, List<AnswerResponse> answers) {
        return DetailQuestionResponse.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .view(question.getView())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .memberResponse(memberResponse)
                .answers(answers)
                .build();
    }
}
