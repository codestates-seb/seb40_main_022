package com.backend.fitchallenge.domain.member.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class MemberExist extends BusinessLogicException {

    public MemberExist(){
        super(ExceptionCode.MEMBER_EXISTS);
    }
}
