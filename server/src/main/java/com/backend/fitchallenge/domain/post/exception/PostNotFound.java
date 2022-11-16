package com.backend.fitchallenge.domain.post.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class PostNotFound extends BusinessLogicException {

    public PostNotFound() {
        super(ExceptionCode.POST_NOT_FOUND);
    }
}


