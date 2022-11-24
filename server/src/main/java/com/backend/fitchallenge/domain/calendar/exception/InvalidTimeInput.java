package com.backend.fitchallenge.domain.calendar.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class InvalidTimeInput extends BusinessLogicException {
    public InvalidTimeInput() {
        super(ExceptionCode.INVALID_TIME_INPUT);
    }
}
