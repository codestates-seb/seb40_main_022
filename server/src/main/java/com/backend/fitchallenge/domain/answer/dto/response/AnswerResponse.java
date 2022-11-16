package com.backend.fitchallenge.domain.answer.dto.response;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AnswerResponse {

    private String content;

    private boolean isAccepted;

    @Builder
    private AnswerResponse(String content, boolean isAccepted) {
        this.content = content;
        this.isAccepted = isAccepted;
    }

    public static AnswerResponse of(Answer answer) {
        return AnswerResponse.builder()
                .content(answer.getContent())
                .isAccepted(answer.isAccepted())
                .build();
    }
}
