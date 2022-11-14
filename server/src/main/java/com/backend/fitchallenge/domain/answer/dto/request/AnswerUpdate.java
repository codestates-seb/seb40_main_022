package com.backend.fitchallenge.domain.answer.dto.request;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
public class AnswerUpdate {

    private String content;

    private boolean isAccepted;
}
