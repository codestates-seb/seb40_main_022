package com.backend.fitchallenge.domain.post.dto;

import com.backend.fitchallenge.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimplePostResponse {

    private Long postId;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String content;
    private Integer commentCount ;
    private Integer likeCount;

    @Builder
    public SimplePostResponse(Long postId, Long viewCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String content, Integer commentCount,Integer likeCount) {
        this.postId = postId;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.content = content;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }

    public static SimplePostResponse toResponse(Post post, Integer likeSize, Integer commentSize) {
        return SimplePostResponse.builder()
                .postId(post.getId())
                .viewCount(post.getView())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .content(post.getContent())
                .likeCount(likeSize)
                .commentCount(commentSize)
                .build();
    }


}
