package com.backend.fitchallenge.domain.answercomment.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnswerCommentUpdate {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
