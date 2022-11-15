package com.backend.fitchallenge.domain.member.dto.response;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;

    private String username;

    private String profileImage;

    @Builder
    private MemberResponse(Long id, String username, String profileImage) {
        this.id = id;
        this.username = username;
        this.profileImage = profileImage;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .profileImage(member.getProfileImage())
                .build();
    }
}
