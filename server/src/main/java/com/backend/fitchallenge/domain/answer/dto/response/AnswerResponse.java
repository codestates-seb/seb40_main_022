package com.backend.fitchallenge.domain.answer.dto.response;

import com.backend.fitchallenge.domain.answer.entity.Answer;

import com.backend.fitchallenge.domain.answercomment.dto.response.AnswerCommentResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AnswerResponse {

    private String content;

    private boolean isAccepted;

    private MemberResponse answerWriter;

    private List<AnswerCommentResponse> comments;

    @Builder
    private AnswerResponse(String content, boolean isAccepted, MemberResponse memberResponse, List<AnswerCommentResponse> comments) {
        this.content = content;
        this.isAccepted = isAccepted;
        this.answerWriter = memberResponse;
        this.comments = comments;
    }

    public static AnswerResponse of(Answer answer, List<AnswerCommentResponse> comments) {
        return AnswerResponse.builder()
                .content(answer.getContent())
                .isAccepted(answer.isAccepted())
                .memberResponse(MemberResponse.of(answer.getMember()))
                .comments(comments)
                .build();
    }
}
