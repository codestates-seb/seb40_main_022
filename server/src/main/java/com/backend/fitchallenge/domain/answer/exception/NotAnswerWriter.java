package com.backend.fitchallenge.domain.answer.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NotAnswerWriter extends BusinessLogicException {
    public NotAnswerWriter() {
        super(ExceptionCode.NOT_ANSWER_WRITER);
    }
}
