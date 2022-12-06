package com.backend.fitchallenge.domain.post.dto;

import com.backend.fitchallenge.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimplePostResponse {

    private Long postId;
    private LocalDateTime createdAt;
    private String content;
    private Integer commentCount ;
    private Integer likeCount;
    private Boolean more;
    private Boolean commentMore;

    @Builder
    public SimplePostResponse(Long postId, LocalDateTime createdAt, String content, Integer commentCount,
                              Integer likeCount, Boolean more, Boolean commentMore) {
        this.postId = postId;
        this.createdAt = createdAt;
        this.content = content;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.more = more;
        this.commentMore = commentMore;
    }

    public static SimplePostResponse of(Post post, Integer likeSize, Integer commentSize) {

        return SimplePostResponse.builder()
                .postId(post.getId())
                .createdAt(post.getCreatedAt())
                .content(post.getContent())
                .likeCount(likeSize)
                .commentCount(commentSize)
                .more(false)
                .commentMore(false)
                .build();
    }
}
