package com.backend.fitchallenge.domain.question.dto.request;

import org.springframework.data.domain.Sort;

public class PageRequestTemp {
    private static final int MAX_SIZE = 2000;

    private int page;
    private int size = 10;
    private String sort;

    public PageRequestTemp(int page, int sort) {
        this.page = page <= 0 ? 1 : page;
        if (sort == 1) {
            this.sort = "latest";
        } else if (sort == 2) {
            this.sort = "hot";
        } else {
            this.sort = "accuracy";
        }
    }

    public String getSort() {
        return this.sort;
    }

    public long getOffset() {
        return (long)(Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }

    // getter
    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1 , size);
    }
}
