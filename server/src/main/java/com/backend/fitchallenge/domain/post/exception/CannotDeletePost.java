package com.backend.fitchallenge.domain.post.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotDeletePost extends BusinessLogicException {

    public CannotDeletePost() {
        super(ExceptionCode.CANNOT_DELETE_POST);
    }
}


