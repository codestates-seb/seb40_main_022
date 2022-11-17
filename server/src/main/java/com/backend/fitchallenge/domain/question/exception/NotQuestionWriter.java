package com.backend.fitchallenge.domain.question.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NotQuestionWriter extends BusinessLogicException {
    public NotQuestionWriter() {
        super(ExceptionCode.NOT_QUESTION_WRITER);
    }
}
