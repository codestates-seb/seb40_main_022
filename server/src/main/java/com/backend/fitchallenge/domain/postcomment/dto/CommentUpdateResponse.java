package com.backend.fitchallenge.domain.postcomment.dto;

import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentUpdateResponse {

    private Long commentId;
    private String content;


    @Builder
    public CommentUpdateResponse(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public static CommentUpdateResponse toResponse(PostComment postComment) {
        return CommentUpdateResponse.builder()
                .commentId(postComment.getId())
                .content(postComment.getContent())
                .build();
    }


}
