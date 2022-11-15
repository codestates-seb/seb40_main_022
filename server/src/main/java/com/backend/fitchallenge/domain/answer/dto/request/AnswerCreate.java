package com.backend.fitchallenge.domain.answer.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerCreate {
    @NotBlank
    private String content;
}
