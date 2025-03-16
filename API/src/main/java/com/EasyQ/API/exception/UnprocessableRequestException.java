package com.EasyQ.API.exception;

import java.time.LocalDateTime;

public class UnprocessableRequestException extends BaseException {
    public UnprocessableRequestException(LocalDateTime localDateTime, String error, String message) {
        super(localDateTime, error, message);
    }
}
