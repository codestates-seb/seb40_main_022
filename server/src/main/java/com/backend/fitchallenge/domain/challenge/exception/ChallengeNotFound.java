package com.backend.fitchallenge.domain.challenge.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class ChallengeNotFound extends BusinessLogicException {

    public ChallengeNotFound() {
        super(ExceptionCode.CHALLENGE_NOT_FOUND);
    }
}


