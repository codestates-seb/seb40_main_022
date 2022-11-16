package com.backend.fitchallenge.domain.answer.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class AnswerException extends BusinessLogicException {

    private ExceptionCode exceptionCode;

    public AnswerException(ExceptionCode exceptionCode) {
        super(exceptionCode);
        this.exceptionCode = exceptionCode;
    }
}
