package com.backend.fitchallenge.domain.question.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.MemberResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleQuestionResponse {
    private Long questionId;

    private String title;

    private String summary;

    private Long view;

    private Integer answerCount;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse memberResponse;

    @Builder
    public SimpleQuestionResponse(Question question, MemberResponse memberResponse, Integer answerCount) {
        this.questionId = question.getId();
        this.title = question.getTitle();
        this.summary = getSummary(question.getContent());
        this.view = question.getView();
        this.answerCount = answerCount;
        this.createdAt = question.getCreatedAt();
        this.modifiedAt = question.getModifiedAt();
        this.memberResponse = memberResponse;
    }

    private String getSummary(String content) {
        return content.length() > 100 ? content.substring(0, 100) + "..." : content;
    }
}
