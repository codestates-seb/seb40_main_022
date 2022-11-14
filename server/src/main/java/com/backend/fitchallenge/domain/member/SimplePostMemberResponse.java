package com.backend.fitchallenge.domain.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SimplePostMemberResponse {

    private Long userId;
    private String profileImage;
    private String username;

    @Builder
    public SimplePostMemberResponse(Long userId, String profileImage, String username) {
        this.userId = userId;
        this.profileImage = profileImage;
        this.username = username;
    }

    @Builder
    public static SimplePostMemberResponse toResponse(Member member) {
        return SimplePostMemberResponse.builder()
                .userId(member.getId())
                .profileImage(member.getProfileImage())
                .username(member.getUsername())
                .build();
    }
}
