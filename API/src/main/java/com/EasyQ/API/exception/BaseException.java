package com.EasyQ.API.exception;

import java.time.LocalDateTime;

public class BaseException {
    private LocalDateTime localDateTime;

    private String error;

    private String message;

    public BaseException(LocalDateTime localDateTime, String error, String message) {
        this.message = message;
        this.error = error;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
