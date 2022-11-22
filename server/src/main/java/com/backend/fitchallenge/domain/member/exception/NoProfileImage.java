package com.backend.fitchallenge.domain.member.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class NoProfileImage extends BusinessLogicException {

    public NoProfileImage(){
        super(ExceptionCode.NO_PROFILE_IMAGE);
    }
}
