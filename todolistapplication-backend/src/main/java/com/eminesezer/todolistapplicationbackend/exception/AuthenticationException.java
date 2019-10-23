package com.eminesezer.todolistapplicationbackend.exception;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -1803791356765432047L;

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}