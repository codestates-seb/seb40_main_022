package com.backend.fitchallenge.domain.member.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.DailyPost;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractMember;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 마이페이지 response dto
@Getter
@NoArgsConstructor
public class DetailsMemberResponse {
    private ExtractMember member;
    private ExtractActivity activity;
    private SliceMultiResponse<DailyPost> dailyPosts;
    private Integer postCounts;

    @Builder
    private DetailsMemberResponse(ExtractMember member, ExtractActivity activity, SliceMultiResponse<DailyPost> dailyPosts,
                                  Integer postCounts) {
        this.member = member;
        this.activity = activity;
        this.dailyPosts = dailyPosts;
        this.postCounts = postCounts;
    }

    //포스트 추가시 사용할
    public static DetailsMemberResponse of(ExtractMember member, ExtractActivity activity, SliceMultiResponse<DailyPost> dailyPosts,
                                           Integer postCount){
        return DetailsMemberResponse.builder()
                .member(member)
                .activity(activity)
                .dailyPosts(dailyPosts)
                .postCounts(postCount)
                .build();
    }
}