package com.backend.fitchallenge.domain.challenge.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotAcceptChallenge extends BusinessLogicException {

    public CannotAcceptChallenge() {
        super(ExceptionCode.CANNOT_ACCEPT_CHALLENGE);
    }
}


