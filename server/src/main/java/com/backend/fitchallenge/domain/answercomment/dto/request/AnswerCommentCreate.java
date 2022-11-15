package com.backend.fitchallenge.domain.answercomment.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerCommentCreate {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
