package com.backend.fitchallenge.domain.challenge.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotSuspendChallenge extends BusinessLogicException {

    public CannotSuspendChallenge() {
        super(ExceptionCode.CANNOT_SUSPEND_CHALLENGE);
    }
}


