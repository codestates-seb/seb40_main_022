package com.backend.fitchallenge.domain.answercomment.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CommentNotFound extends BusinessLogicException {
    public CommentNotFound() {
        super(ExceptionCode.COMMENT_NOT_FOUND);
    }
}
