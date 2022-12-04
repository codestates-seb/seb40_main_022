package com.backend.fitchallenge.domain.record.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SimpleRecordResponse {
    List<PersonalSimpleRecordResponse> member;
    List<PersonalSimpleRecordResponse> opponent;
    Long challengeId;

    @Builder
    private SimpleRecordResponse(List<PersonalSimpleRecordResponse> member,
                                 List<PersonalSimpleRecordResponse> opponent, Long challengeId) {
        this.member = member;
        this.opponent = opponent;
        this.challengeId = challengeId;
    }

    public static SimpleRecordResponse containingOnly(List<PersonalSimpleRecordResponse> member) {
        return SimpleRecordResponse.builder()
                .member(member)
                .build();
    }

    public static SimpleRecordResponse containingBoth(List<PersonalSimpleRecordResponse> member,
                                                      List<PersonalSimpleRecordResponse> opponent,
                                                      Long challengeId) {
        return SimpleRecordResponse.builder()
                .member(member)
                .opponent(opponent)
                .challengeId(challengeId)
                .build();
    }
}
