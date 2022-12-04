package com.backend.fitchallenge.domain.postcomment.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotDeleteComment extends BusinessLogicException {

    public CannotDeleteComment() {
        super(ExceptionCode.CANNOT_DELETE_COMMENT);
    }
}


