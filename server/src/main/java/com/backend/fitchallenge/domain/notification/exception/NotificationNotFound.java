package com.backend.fitchallenge.domain.notification.exception;

import com.backend.fitchallenge.global.error.exception.BusinessLogicException;
import com.backend.fitchallenge.global.error.exception.ExceptionCode;

public class  NotificationNotFound extends BusinessLogicException {

public NotificationNotFound(){
        super(ExceptionCode.NOTIFICATION_NOT_FOUND); }
}
