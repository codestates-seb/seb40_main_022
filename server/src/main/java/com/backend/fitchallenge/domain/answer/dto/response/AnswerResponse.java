package com.backend.fitchallenge.domain.answer.dto.response;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AnswerResponse {

    private String content;

    private boolean isAccepted;

    private MemberResponse answerWriter;

    @Builder
    private AnswerResponse(String content, boolean isAccepted, MemberResponse memberResponse) {
        this.content = content;
        this.isAccepted = isAccepted;
        this.answerWriter = memberResponse;
    }

    public static AnswerResponse of(Answer answer) {
        return AnswerResponse.builder()
                .content(answer.getContent())
                .isAccepted(answer.isAccepted())
                .memberResponse(MemberResponse.of(answer.getMember()))
                .build();
    }
}
