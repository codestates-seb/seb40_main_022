package com.backend.fitchallenge.domain.question.dto.request;

import lombok.Getter;

@Getter
public class PageRequest {
    private static final int MAX_SIZE = 2000;

    private int page;
    private int size = 10;
    private String sort;

    public PageRequest(Integer page, Integer sort) {
        this.page = page == null || page <= 0 ? 1 : page;
        this.sort = sort == null || sort == 0 ? "accuracy" : sort == 1 ? "id" : sort == 2 ? "view" : "accuracy";
    }

    public long getOffset() {
        return (long)(Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1 , size);
    }
}
