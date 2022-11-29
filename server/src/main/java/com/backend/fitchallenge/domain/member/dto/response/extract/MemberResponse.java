package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;

    private String username;

    private String profileImage;

    @QueryProjection
    public MemberResponse(Long id, String username, String profileImage) {
        this.id = id;
        this.username = username;
        this.profileImage = profileImage;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getUsername(),
                member.getProfileImage().getPath());
    }
}
