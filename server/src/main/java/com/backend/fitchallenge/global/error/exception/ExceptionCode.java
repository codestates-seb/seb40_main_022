package com.backend.fitchallenge.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    TOKEN_NOT_FOUND(404, "Token not found"),
    TOKEN_NOT_VALID(500, "Token not valid"),

    QUESTION_NOT_FOUND(404, "Question not found"),
    NOT_QUESTION_WRITER(400, "Not question writer"),

    ANSWER_NOT_FOUND(404, "Answer not found"),
    NOT_ANSWER_WRITER(400, "Not answer writer"),

    COMMENT_NOT_FOUND(404, "Comment not found"),
    NOT_COMMENT_WRITER(400, "Not comment writer"),

    CHALLENGE_NOT_FOUND(404, "Challenge not found"),
    CANNOT_REFUSE_CHALLENGE(403, "Cannot Refuse Challenge"),

    //Post
    POST_NOT_FOUND(404, "Post Not Found"),
    NO_IMAGE(404, "Image Is Empty"),
    UPLOAD_FAILED(404, "Upload Failed"),
    CANNOT_UPDATE_POST(403, "Cannot Update Answer"),
    CANNOT_DELETE_POST(403, "Cannot Delete Answer"),

    //Comment
    CANNOT_UPDATE_COMMENT(403, "Cannot Update Comment"),
    CANNOT_DELETE_COMMENT(403, "Cannot Delete Comment"),
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
