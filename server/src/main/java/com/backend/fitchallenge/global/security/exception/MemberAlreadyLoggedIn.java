package com.backend.fitchallenge.global.security.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class MemberAlreadyLoggedIn extends BusinessLogicException {

    public MemberAlreadyLoggedIn() {
        super(ExceptionCode.MEMBER_ALREADY_LOGGED_IN);
    }
}
