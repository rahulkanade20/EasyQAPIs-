package com.EasyQ.API.exception;

import java.time.LocalDateTime;

public class NotFoundException extends BaseException {
    public NotFoundException(LocalDateTime localDateTime, String error, String message) {
        super(localDateTime, error, message);
    }
}
