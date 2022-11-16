package com.backend.fitchallenge.domain.post.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NoImage extends BusinessLogicException {

    public NoImage() {
        super(ExceptionCode.NO_IMAGE);
    }
}


