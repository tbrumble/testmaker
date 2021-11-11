package ru.tbrumble.testmaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SwitchException extends RuntimeException{
    public SwitchException() {}

    public SwitchException(String message) {
        super(message);
    }

    public SwitchException(String message, Throwable cause) {
        super(message, cause);
    }

    public SwitchException(Throwable cause) {
        super(cause);
    }

    public SwitchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
