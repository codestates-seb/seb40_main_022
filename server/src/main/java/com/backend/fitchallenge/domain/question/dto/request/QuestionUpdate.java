package com.backend.fitchallenge.domain.question.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionUpdate {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String tag;

    @Builder
    public QuestionUpdate(String title, String content, String tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }
}
