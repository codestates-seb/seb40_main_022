package com.backend.fitchallenge.domain.answer.dto.response;

import com.backend.fitchallenge.domain.answer.entity.Answer;

import com.backend.fitchallenge.domain.answercomment.dto.response.AnswerCommentResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;

import com.backend.fitchallenge.domain.postcomment.dto.CommentResponse;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnswerResponse {

    private Long answerId;

    private String content;

    private Boolean accepted;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse answerWriter;

    private List<AnswerCommentResponse> comments;

    @Builder
    public AnswerResponse(Long answerId,
                           String content,
                           boolean isAccepted,
                           LocalDateTime createdAt,
                           LocalDateTime modifiedAt,
                           MemberResponse answerWriter,
                           List<AnswerCommentResponse> comments) {
        this.answerId = answerId;
        this.content = content;
        this.accepted = isAccepted;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.answerWriter = answerWriter;
        this.comments = comments;
    }

    public static AnswerResponse of(Answer answer, List<AnswerCommentResponse> comments) {
        return AnswerResponse.builder()
                .answerId(answer.getId())
                .content(answer.getContent())
                .isAccepted(answer.isAccepted())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .answerWriter(MemberResponse.of(answer.getMember()))
                .comments(comments)
                .build();
    }

}
