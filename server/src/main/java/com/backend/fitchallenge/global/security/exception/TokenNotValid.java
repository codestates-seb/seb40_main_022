package com.backend.fitchallenge.global.security.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class TokenNotValid extends BusinessLogicException {

    public TokenNotValid(){
        super(ExceptionCode.TOKEN_NOT_VALID);
    }
}
