package com.backend.fitchallenge.domain.answercomment.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class AnswerCommentException extends BusinessLogicException {

    private ExceptionCode exceptionCode;

    public AnswerCommentException(ExceptionCode exceptionCode) {
        super(exceptionCode);
        this.exceptionCode = exceptionCode;
    }
}
