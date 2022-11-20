package com.backend.fitchallenge.domain.calendar.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class RecordNotFound extends BusinessLogicException {
    public RecordNotFound() {
        super(ExceptionCode.RECORD_NOT_FOUND);
    }
}
