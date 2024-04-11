package com.EasyQ.API.exceptions;

import com.fasterxml.jackson.databind.ser.Serializers;

import java.time.LocalDateTime;

public class UnprocessableRequestException extends BaseException {
    public UnprocessableRequestException(LocalDateTime localDateTime, String error, String message) {
        super(localDateTime, error, message);
    }
}
