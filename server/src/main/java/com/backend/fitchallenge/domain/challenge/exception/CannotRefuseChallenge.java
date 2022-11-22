package com.backend.fitchallenge.domain.challenge.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotRefuseChallenge extends BusinessLogicException {

    public CannotRefuseChallenge() {
        super(ExceptionCode.CANNOT_REFUSE_CHALLENGE);
    }
}


