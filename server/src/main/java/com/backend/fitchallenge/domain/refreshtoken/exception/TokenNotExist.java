package com.backend.fitchallenge.domain.refreshtoken.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class TokenNotExist extends BusinessLogicException {

    public TokenNotExist(){
        super(ExceptionCode.TOKEN_NOT_FOUND);
    }
}
