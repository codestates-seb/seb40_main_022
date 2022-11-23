package com.backend.fitchallenge.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    //Member
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_ALREADY_LOGGED_IN(403, "Member already logged in"),
    NO_PROFILE_IMAGE(404, "Image Is Empty"),

    TOKEN_NOT_FOUND(404, "Token not found"),
    TOKEN_NOT_VALID(401, "Token not valid"),

    //Question
    QUESTION_NOT_FOUND(404, "Question not found"),
    NOT_QUESTION_WRITER(400, "Not question writer"),
    QUESTION_TAG_NOT_VALID(400, "Question Tag Not Valid"),
    //Answer
    ANSWER_NOT_FOUND(404, "Answer not found"),
    NOT_ANSWER_WRITER(400, "Not answer writer"),


    CHALLENGE_NOT_FOUND(404, "Challenge not found"),
    CANNOT_REFUSE_CHALLENGE(403, "Cannot Refuse Challenge"),
    CANNOT_SUGGEST_CHALLENGE(404, "Cannot Suggest Challenge"),
    CANNOT_SUSPEND_CHALLENGE(403, "Cannot Suspend Challenge"),
    CANNOT_ACCEPT_CHALLENGE(404, "Cannot Accept Challenge "),

    //Post
    POST_NOT_FOUND(404, "Post Not Found"),
    NO_IMAGE(404, "Image Is Empty"),
    UPLOAD_FAILED(404, "Upload Failed"),
    CANNOT_UPDATE_POST(403, "Cannot Update Answer"),
    CANNOT_DELETE_POST(403, "Cannot Delete Answer"),

    //Comment
    COMMENT_NOT_FOUND(404, "Comment not found"),
    NOT_COMMENT_WRITER(400, "Not comment writer"),

    CANNOT_UPDATE_COMMENT(403, "Cannot Update Comment"),
    CANNOT_DELETE_COMMENT(403, "Cannot Delete Comment"),
    //Tag
    TAG_NOT_FOUND(404, "Tag Not Found"),

    //Record
    RECORD_NOT_FOUND(404, "Record not found"),
    NOT_RECORD_WRITER(400, "Not record writer"),
    DUPLICATE_RECORD_CREATION(400, "Duplicate Record Creation"),
    //Calendar
    CALENDAR_NOT_FOUND(404, "Calendar not found"),
    //Sports
    SPORTS_NOT_FOUND(404, "Sports not found");


    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
