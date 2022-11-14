package com.backend.fitchallenge.domain.member.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractMember;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 마이페이지 response dto
@Getter
@NoArgsConstructor
public class MyPageResponse {
    private ExtractMember member;
    private ExtractActivity activity;
    private List<ExtractPost> dailyPosts;

    @Builder
    private MyPageResponse(ExtractMember member, ExtractActivity activity, List<ExtractPost> dailyPosts) {
        this.member = member;
        this.activity = activity;
        this.dailyPosts = dailyPosts;
    }

    //포스트 추가시 사용할 것
    public static MyPageResponse of(ExtractMember member, ExtractActivity activity, List<ExtractPost> dailyPosts){
        return MyPageResponse.builder()
                .member(member)
                .activity(activity)
                .dailyPosts(dailyPosts)
                .build();
    }

    //임시
    public static MyPageResponse of(ExtractMember member, ExtractActivity activity){
        return MyPageResponse.builder()
                .member(member)
                .activity(activity)
                .build();
    }
}