package com.backend.fitchallenge.domain.member.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 특정 유저정보를 조회하기위한 responseDto
@Getter
@NoArgsConstructor
public class DetailsMemberResponse {
    private String userName;
    private ExtractActivity activity;
    private List<ExtractPost> dailyPosts;

    @Builder
    private DetailsMemberResponse(String userName, ExtractActivity activity, List<ExtractPost> dailyPosts) {
        this.userName = userName;
        this.activity = activity;
        this.dailyPosts = dailyPosts;
    }

    //포스트 추가시 사용할 것
    public static DetailsMemberResponse of(String userName, ExtractActivity activity, List<ExtractPost> dailyPosts){
        return DetailsMemberResponse.builder()
                .userName(userName)
                .activity(activity)
                .dailyPosts(dailyPosts)
                .build();
    }

    //임시
    public static DetailsMemberResponse of(String userName, ExtractActivity activity){
        return DetailsMemberResponse.builder()
                .userName(userName)
                .activity(activity)
                .build();
    }
}