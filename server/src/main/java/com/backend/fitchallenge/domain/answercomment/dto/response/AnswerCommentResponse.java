package com.backend.fitchallenge.domain.answercomment.dto.response;

import com.backend.fitchallenge.domain.answercomment.entity.AnswerComment;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerCommentResponse {

    private Long answerCommentId;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse commentWriter;

    @Builder
    private AnswerCommentResponse(Long answerCommentId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, MemberResponse commentWriter) {
        this.answerCommentId = answerCommentId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentWriter = commentWriter;
    }

    public static AnswerCommentResponse of(AnswerComment answerComment) {
        return AnswerCommentResponse.builder()
                .answerCommentId(answerComment.getId())
                .content(answerComment.getContent())
                .createdAt(answerComment.getCreatedAt())
                .modifiedAt(answerComment.getModifiedAt())
                .commentWriter(MemberResponse.of(answerComment.getMember()))
                .build();
    }
}
