package com.backend.fitchallenge.domain.postcomment.dto;

import lombok.Getter;

@Getter
public class CommentGet {
    private Long lastCommentId;

    public CommentGet(Long lastCommentId) {
        this.lastCommentId = lastCommentId;
    }
}
