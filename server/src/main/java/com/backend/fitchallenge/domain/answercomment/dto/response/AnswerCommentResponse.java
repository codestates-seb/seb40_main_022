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

    private MemberResponse memberResponse;

    @Builder
    private AnswerCommentResponse(AnswerComment answerComment, MemberResponse memberResponse) {
        this.content = answerComment.getContent();
        this.createdAt = answerComment.getCreatedAt();
        this.modifiedAt = answerComment.getModifiedAt();
        this.memberResponse = memberResponse;
    }

    public static AnswerCommentResponse of(AnswerComment answerComment, MemberResponse memberResponse) {
        return AnswerCommentResponse.builder()
                .answerComment(answerComment)
                .memberResponse(memberResponse)
                .build();
    }
}
