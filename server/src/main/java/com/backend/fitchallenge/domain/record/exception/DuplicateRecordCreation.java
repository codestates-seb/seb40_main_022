package com.backend.fitchallenge.domain.record.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class DuplicateRecordCreation extends BusinessLogicException {
    public DuplicateRecordCreation() {
        super(ExceptionCode.DUPLICATE_RECORD_CREATION);
    }
}
