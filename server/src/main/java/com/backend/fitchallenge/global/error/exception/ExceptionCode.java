package com.backend.fitchallenge.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    TOKEN_NOT_FOUND(404, "Token not found"),
    TOKEN_NOT_VALID(500, "Token not valid");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
