package com.backend.fitchallenge.global.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class MultiResponse<T> {
    private List<T> items;
    private PageInfo pageInfo;


    private MultiResponse(Slice<T> items) {
        this.items = items.getContent();
        this.pageInfo = PageInfo.builder()
                .page(items.getNumber()+1)
                .size(items.getSize())
                .build();
    }

    public static MultiResponse<?> of(Slice<?> items){
        return new MultiResponse<>(items);

    }

    @Getter
    static class PageInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;

        @Builder
        public PageInfo(int page, int size, long totalElements, int totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }



    }

}
