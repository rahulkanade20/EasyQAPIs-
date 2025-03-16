package com.EasyQ.API.exception;

import java.time.LocalDateTime;

public class ServerException extends BaseException {
    public ServerException(LocalDateTime localDateTime, String error, String message) {
        super(localDateTime, error, message);
    }
}
