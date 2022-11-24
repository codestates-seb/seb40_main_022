package com.backend.fitchallenge.domain.calendar.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class PictureDateMismatch extends BusinessLogicException {
    public PictureDateMismatch() {
        super(ExceptionCode.PICTURE_DATE_MISMATCH);
    }
}
