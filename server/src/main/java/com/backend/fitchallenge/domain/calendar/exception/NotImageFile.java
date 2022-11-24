package com.backend.fitchallenge.domain.calendar.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NotImageFile extends BusinessLogicException {
    public NotImageFile() {
        super(ExceptionCode.NOT_IMAGE_FILE);
    }
}
