package com.backend.fitchallenge.domain.challenge.dto.request;

import org.springframework.data.domain.Sort;

public final class PageRequest {

        private int page = 1;
        private int size = 10;
        private Sort.Direction direction = Sort.Direction.DESC;

    public PageRequest(int page ) {
        this.page = page <= 0 ? 1 : page;

    }

        public org.springframework.data.domain.PageRequest of() {
            return org.springframework.data.domain.PageRequest.of(page -1, size);
        }
}
