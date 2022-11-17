package com.backend.fitchallenge.domain.workout.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NotRecordWriter extends BusinessLogicException {
    public NotRecordWriter() {
        super(ExceptionCode.NOT_RECORD_WRITER);
    }
}
