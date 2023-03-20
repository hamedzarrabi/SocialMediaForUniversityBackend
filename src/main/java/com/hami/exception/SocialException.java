package com.hami.exception;

import org.springframework.http.HttpStatus;

public class SocialException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public SocialException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public SocialException(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
