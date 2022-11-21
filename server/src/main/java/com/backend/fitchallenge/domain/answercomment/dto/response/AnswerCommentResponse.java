package com.backend.fitchallenge.domain.answercomment.dto.response;

import com.backend.fitchallenge.domain.answercomment.entity.AnswerComment;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerCommentResponse {

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse commentWriter;

    @Builder
    private AnswerCommentResponse(AnswerComment answerComment, MemberResponse commentWriter) {
        this.content = answerComment.getContent();
        this.createdAt = answerComment.getCreatedAt();
        this.modifiedAt = answerComment.getModifiedAt();
        this.commentWriter = commentWriter;
    }

    public static AnswerCommentResponse of(AnswerComment answerComment) {
        return AnswerCommentResponse.builder()
                .answerComment(answerComment)
                .commentWriter(MemberResponse.of(answerComment.getMember()))
                .build();
    }
}
