package ru.tbrumble.testmaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class VerifyException extends RuntimeException{

    public VerifyException() {}

    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyException(Throwable cause) {
        super(cause);
    }

    public VerifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
