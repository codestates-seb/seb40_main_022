package com.backend.fitchallenge.domain.question.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class QuestionTagNotValid extends BusinessLogicException {
    public QuestionTagNotValid() {
        super(ExceptionCode.QUESTION_TAG_NOT_VALID);
    }
}
