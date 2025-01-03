package com.multiwarehouse.app.authgateway.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class AuthUnauthorizedException extends DomainException {

    public AuthUnauthorizedException(String message) {
        super(message);
    }

    public AuthUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
