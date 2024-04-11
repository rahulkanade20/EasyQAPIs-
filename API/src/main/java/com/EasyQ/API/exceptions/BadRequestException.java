package com.EasyQ.API.exceptions;

import java.time.LocalDateTime;

public class BadRequestException extends BaseException {

    public BadRequestException(LocalDateTime localDateTime, String message, String error) {
        super(localDateTime, message, error);
    }
}
