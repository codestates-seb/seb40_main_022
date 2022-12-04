package com.backend.fitchallenge.domain.post.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Slf4j
public class PostGet {

    private Long lastPostId;

    public PostGet(Long lastPostId) {
        this.lastPostId = lastPostId;
    }
}
