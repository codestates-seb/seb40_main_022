package com.backend.fitchallenge.domain.record.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DetailRecordResponse {

    private PersonalDetailRecordResponse member;

    private PersonalDetailRecordResponse opponent;

    @Builder
    private DetailRecordResponse(PersonalDetailRecordResponse member,
                                 PersonalDetailRecordResponse opponent) {
        this.member = member;
        this.opponent = opponent;
    }

    public static DetailRecordResponse containingOnly(PersonalDetailRecordResponse member) {
        return DetailRecordResponse.builder()
                .member(member)
                .build();
    }

    public static DetailRecordResponse containingBoth(PersonalDetailRecordResponse member, PersonalDetailRecordResponse opponent) {
        return DetailRecordResponse.builder()
                .member(member)
                .opponent(opponent)
                .build();
    }
}
