package com.backend.fitchallenge.domain.workout.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RecordResponse {
    private List<SimpleRecordResponse> responses;

    @Builder
    private RecordResponse(List<SimpleRecordResponse> responses) {
        this.responses = responses;
    }

    public static RecordResponse of(List<SimpleRecordResponse> responses) {
        return RecordResponse.builder()
                .responses(responses)
                .build();
    }
}
