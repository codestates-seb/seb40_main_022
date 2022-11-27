package com.backend.fitchallenge.domain.sports.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class SportsNotFound extends BusinessLogicException {
    public SportsNotFound() {
        super(ExceptionCode.SPORTS_NOT_FOUND);
    }
}
