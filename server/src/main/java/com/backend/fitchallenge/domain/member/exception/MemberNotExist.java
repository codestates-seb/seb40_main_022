package com.backend.fitchallenge.domain.member.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class MemberNotExist extends BusinessLogicException {

    public MemberNotExist(){
        super(ExceptionCode.MEMBER_NOT_FOUND);
    }
}
