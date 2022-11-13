package com.backend.fitchallenge.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),

    //Post-Image
    NO_IMAGE(404, "Image Is Empty"),
    UPLOAD_FAILED(404, "Upload Failed"),

    //Tag
    TAG_NOT_FOUND(404, "Tag Not Found");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
