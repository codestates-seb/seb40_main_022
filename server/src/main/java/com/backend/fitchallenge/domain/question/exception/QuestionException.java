package com.backend.fitchallenge.domain.question.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class QuestionException extends BusinessLogicException {

    private ExceptionCode exceptionCode;

    public QuestionException(ExceptionCode exceptionCode) {
        super(exceptionCode);
        this.exceptionCode = exceptionCode;
    }
}
