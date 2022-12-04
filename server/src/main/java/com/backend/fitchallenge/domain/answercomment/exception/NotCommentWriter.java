package com.backend.fitchallenge.domain.answercomment.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NotCommentWriter extends BusinessLogicException {
    public NotCommentWriter() {
        super(ExceptionCode.NOT_COMMENT_WRITER);
    }
}
