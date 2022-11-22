package com.backend.fitchallenge.domain.challenge.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class CannotSuggestChallenge extends BusinessLogicException {

    public CannotSuggestChallenge() {
        super(ExceptionCode.CANNOT_SUGGEST_CHALLENGE);
    }
}


