package com.backend.fitchallenge.domain.tag.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class TagNotFound extends BusinessLogicException {

    public TagNotFound() {
        super(ExceptionCode.TAG_NOT_FOUND);
    }
}
