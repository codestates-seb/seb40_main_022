package com.backend.fitchallenge.domain.calendar.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CalendarNotFound extends BusinessLogicException {
    public CalendarNotFound() {
        super(ExceptionCode.CALENDAR_NOT_FOUND);
    }
}
