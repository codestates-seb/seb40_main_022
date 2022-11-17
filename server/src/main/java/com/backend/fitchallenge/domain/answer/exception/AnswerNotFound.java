package com.backend.fitchallenge.domain.answer.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class AnswerNotFound extends BusinessLogicException {
    public AnswerNotFound() {
        super(ExceptionCode.ANSWER_NOT_FOUND);
    }
}
