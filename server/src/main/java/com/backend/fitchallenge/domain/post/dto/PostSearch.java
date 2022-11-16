package com.backend.fitchallenge.domain.post.dto;

import lombok.Getter;

@Getter
public class PostSearch {

    private String q;

    public PostSearch(String q) {
        this.q = q;
    }


}
