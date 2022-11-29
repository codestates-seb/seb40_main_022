package com.backend.fitchallenge.domain.record.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SimpleRecordResponse {
    List<PersonalSimpleRecordResponse> member;
    List<PersonalSimpleRecordResponse> opponent;

    @Builder
    private SimpleRecordResponse(List<PersonalSimpleRecordResponse> member,
                                 List<PersonalSimpleRecordResponse> opponent) {
        this.member = member;
        this.opponent = opponent;
    }

    public static SimpleRecordResponse containingOnly(List<PersonalSimpleRecordResponse> member) {
        return SimpleRecordResponse.builder()
                .member(member)
                .build();
    }

    public static SimpleRecordResponse containingBoth(List<PersonalSimpleRecordResponse> member,
                                                      List<PersonalSimpleRecordResponse> opponent) {
        return SimpleRecordResponse.builder()
                .member(member)
                .opponent(opponent)
                .build();
    }
}
