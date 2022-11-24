package com.backend.fitchallenge.domain.member.dto.response.extract;

import com.backend.fitchallenge.domain.post.entity.Picture;
import com.backend.fitchallenge.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

// 마이페이지, 특정 유저 정보 조회를 위해 필요 정보를 추출하는 클래스
@Getter
public class DailyPost {
    private Long postId;
    private String image;

    @Builder
    public DailyPost(Long postId, String image) {
        this.postId = postId;
        this.image = image;
    }

    public static DailyPost of(Post post){
        return DailyPost.builder()
                .postId(post.getId())
                .image(post.getPictures().get(0).getPath())
                .build();
    }

    public static DailyPost of(){
        return DailyPost.builder()
                .build();
    }
}