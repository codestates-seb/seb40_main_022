package com.backend.fitchallenge.domain.post.dto;

import com.backend.fitchallenge.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostUpdateResponse {

    private Long postId;
    private String content;
    private List<String> tags;
    private List<String> imagePaths;

@Builder
    public PostUpdateResponse(Long postId, String content, List<String> tags, List<String> imagePaths) {
        this.postId = postId;
        this.content = content;
        this.tags = tags;
        this.imagePaths = imagePaths;
    }


    public static PostUpdateResponse of(Post post) {

      return  PostUpdateResponse.builder()
                .postId(post.getId())
                .content(post.getContent())
                .tags(post.getPostTags().stream().map(postTag -> postTag.getTag().getContent()).collect(Collectors.toList()))
                .imagePaths(post.getPictures().stream().map(picture -> picture.getPath()).collect(Collectors.toList()))
                .build();
    }
}
