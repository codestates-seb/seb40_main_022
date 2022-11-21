package com.backend.fitchallenge.domain.question.dto.response;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DetailQuestionResponse {

    private String title;

    private String content;

    private String tag;

    private Long view;

    private Integer answerCount;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse questionWriter;

    private List<AnswerResponse> answers;

    @Builder
    private DetailQuestionResponse(String title, String content, String tag, Long view, Integer answerCount, LocalDateTime createdAt, LocalDateTime modifiedAt, MemberResponse memberResponse, List<AnswerResponse> answers) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.view = view;
        this.answerCount = answerCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.questionWriter = memberResponse;
        this.answers = answers;
    }

    public static DetailQuestionResponse of(Question question, MemberResponse memberResponse, List<AnswerResponse> answers) {
        return DetailQuestionResponse.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getQuestionTag().getValue())
                .view(question.getView())
                .answerCount(answers.size())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .memberResponse(memberResponse)
                .answers(answers)
                .build();
    }
}
