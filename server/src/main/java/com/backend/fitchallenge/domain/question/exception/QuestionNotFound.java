package com.backend.fitchallenge.domain.question.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class QuestionNotFound extends BusinessLogicException {
    public QuestionNotFound() {
        super(ExceptionCode.QUESTION_NOT_FOUND);
    }
}
