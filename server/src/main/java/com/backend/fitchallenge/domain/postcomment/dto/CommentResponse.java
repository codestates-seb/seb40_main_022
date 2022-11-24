package com.backend.fitchallenge.domain.postcomment.dto;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {

    private Long commentId;
    private String content;
    private Long memberId;
    private String userName;
    private String profileImage;


    @Builder
    public CommentResponse(Long commentId, String content, Long memberId, String userName, String profileImage) {
        this.commentId = commentId;
        this.content = content;
        this.memberId = memberId;
        this.userName = userName;
        this.profileImage = profileImage;
    }

    public static CommentResponse toResponse(PostComment postComment, Member member) {
        return CommentResponse.builder()
                .commentId(postComment.getId())
                .content(postComment.getContent())
                .memberId(member.getId())
                .userName(member.getUsername())
                .build();
    }


}
