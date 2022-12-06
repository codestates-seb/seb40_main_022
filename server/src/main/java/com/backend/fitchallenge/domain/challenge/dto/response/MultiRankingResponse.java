package com.backend.fitchallenge.domain.challenge.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiRankingResponse<T> {

    private List<T> responses;
    private Boolean myChallengeStatus;
    private PageInfo pageInfo;

    public MultiRankingResponse(Page<T> responses, Boolean myChallengeStatus) {

        this.responses = responses.getContent();
        this.myChallengeStatus = myChallengeStatus;
        this.pageInfo = PageInfo.builder()
                .page(responses.getNumber()+1)
                .size(responses.getSize())
                .totalElements(responses.getTotalElements())
                .totalPages(responses.getTotalPages())
                .build();
    }


    public MultiRankingResponse(Page<T> responses) {

        this.responses = responses.getContent();
        this.pageInfo = PageInfo.builder()
                .page(responses.getNumber()+1)
                .size(responses.getSize())
                .totalElements(responses.getTotalElements())
                .totalPages(responses.getTotalPages())
                .build();
    }


    public static MultiRankingResponse<?> withLogin(Page<?> responses, Boolean myChallengeStatus) {
        return new MultiRankingResponse<>(responses, myChallengeStatus);
    }


    public static MultiRankingResponse<?> withoutLogin(Page<?> responses) {
        return new MultiRankingResponse<>(responses);
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
