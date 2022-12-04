package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.backend.fitchallenge.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

//마이페이지 response를 위해 필요 정보를 추출하는 클래스
@Getter
public class ExtractMember{
    private String userName;
    private String profileImage;
    private Integer height;
    private Integer Weight;

    @Builder
    private ExtractMember(String userName, String profileImage, Integer height, Integer weight) {
        this.userName = userName;
        this.profileImage = profileImage;
        this.height = height;
        this.Weight = weight;
    }

    public static ExtractMember of(Member member){
        return ExtractMember.builder()
                .userName(member.getUsername())
                .profileImage(member.getProfileImage().getPath())
                .height(member.getHeight())
                .weight(member.getWeight())
                .build();
    }
}