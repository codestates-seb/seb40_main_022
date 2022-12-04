package com.backend.fitchallenge.domain.record.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecordResponse {
    private List<SimpleRecordResponse> records = new ArrayList<>();

    private MemberResponse member;

    @Builder
    private RecordResponse(List<SimpleRecordResponse> records, MemberResponse member) {
        this.records = records;
        this.member = member;
    }

    public static RecordResponse of(List<SimpleRecordResponse> records, MemberResponse member) {
        return RecordResponse.builder()
                .records(records)
                .member(member)
                .build();
    }
}
