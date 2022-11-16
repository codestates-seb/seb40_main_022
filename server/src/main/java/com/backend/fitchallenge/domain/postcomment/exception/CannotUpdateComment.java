package com.backend.fitchallenge.domain.postcomment.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotUpdateComment extends BusinessLogicException {

    public CannotUpdateComment() {
        super(ExceptionCode.CANNOT_UPDATE_COMMENT);
    }
}


