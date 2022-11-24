package com.backend.fitchallenge.domain.member.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.DailyPost;
import com.backend.fitchallenge.domain.post.entity.Picture;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

// 특정 유저정보를 조회하기위한 responseDto
@Getter
@NoArgsConstructor
public class MyPageResponse {
    private String userName;
    private ExtractActivity activity;
    private List<DailyPost> dailyPosts;

    @Builder
    private MyPageResponse(String userName, ExtractActivity activity, List<DailyPost> dailyPosts) {
        this.userName = userName;
        this.activity = activity;
        this.dailyPosts = dailyPosts;
    }

    //포스트 추가시 사용할 것
    public static MyPageResponse of(String userName, ExtractActivity activity, List<DailyPost> dailyPosts){
        return MyPageResponse.builder()
                .userName(userName)
                .activity(activity)
                .dailyPosts(dailyPosts)
                .build();
    }

    //임시
    public static MyPageResponse of(String userName, ExtractActivity activity){
        return MyPageResponse.builder()
                .userName(userName)
                .activity(activity)
                .build();
    }
}