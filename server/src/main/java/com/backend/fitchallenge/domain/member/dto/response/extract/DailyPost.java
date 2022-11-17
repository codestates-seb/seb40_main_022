package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.backend.fitchallenge.domain.post.entity.Picture;
import lombok.Builder;
import lombok.Getter;

// 마이페이지, 특정 유저 정보 조회를 위해 필요 정보를 추출하는 클래스
@Getter
public class DailyPost {
    private String image;

    @Builder
    private DailyPost(String image) {
        this.image = image;
    }

    public static DailyPost of(Picture picture){
        return DailyPost.builder()
                .image(picture.getPath())
                .build();
    }
}