package com.multiwarehouse.app.authgateway.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class AuthNotFoundException extends DomainException {

    public AuthNotFoundException(String message) {
        super(message);
    }

    public AuthNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
