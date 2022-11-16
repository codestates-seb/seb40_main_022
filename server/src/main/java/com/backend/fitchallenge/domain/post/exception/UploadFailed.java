package com.backend.fitchallenge.domain.post.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class UploadFailed extends BusinessLogicException {

    public UploadFailed() {
        super(ExceptionCode.UPLOAD_FAILED);
    }
}


