package com.backend.fitchallenge.domain.post.dto;

import com.backend.fitchallenge.domain.member.SimplePostMemberResponse;
import com.backend.fitchallenge.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse {

    private List<String>  tags;

    private SimplePostMemberResponse member;

    private SimplePostResponse post;

    private List<String>  pictures;

    @Builder
    public PostResponse(List<String> tags, SimplePostMemberResponse member, SimplePostResponse post, List<String> pictures) {
        this.tags = tags;
        this.member = member;
        this.post = post;
        this.pictures = pictures;
    }

}
