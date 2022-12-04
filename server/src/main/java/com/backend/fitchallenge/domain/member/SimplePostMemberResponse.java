package com.backend.fitchallenge.domain.member;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

//fixme: MemberResponse로 통일
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


    public static SimplePostMemberResponse toResponse(Member member) {
        return SimplePostMemberResponse.builder()
                .userId(member.getId())
                .profileImage(member.getProfileImage().getPath())
                .username(member.getUsername())
                .build();
    }
}
