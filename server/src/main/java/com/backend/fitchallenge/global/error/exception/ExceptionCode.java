package com.backend.fitchallenge.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),

    QUESTION_NOT_FOUND(404, "Question not found"),
    NOT_QUESTION_WRITER(400, "Not question writer"),

    ANSWER_NOT_FOUND(404, "Answer not found"),
    NOT_ANSWER_WRITER(400, "Not answer writer"),

    COMMENT_NOT_FOUND(404, "Comment not found"),
    NOT_COMMENT_WRITER(400, "Not comment writer");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
