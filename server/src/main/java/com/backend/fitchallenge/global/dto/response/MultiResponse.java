package com.backend.fitchallenge.global.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponse<T> {
    private List<T> data;
    private PageInfo pageInfo;

    private MultiResponse(Page<T> items) {
        this.data = items.getContent();
        this.pageInfo = PageInfo.builder()
                .page(items.getNumber() + 1)
                .size(items.getSize())
                .totalElements(items.getNumberOfElements())
                .totalPages(items.getTotalPages())
                .build();
    }

    public static MultiResponse<?> of(Page<?> items) {
        return new MultiResponse(items);
    }

    static class PageInfo {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;

        @Builder
        public PageInfo(int page, int size, int totalElements, int totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }
    }
}
