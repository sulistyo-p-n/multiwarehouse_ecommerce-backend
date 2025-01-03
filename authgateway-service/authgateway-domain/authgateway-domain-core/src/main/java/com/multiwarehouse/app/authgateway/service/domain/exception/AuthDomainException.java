package com.multiwarehouse.app.authgateway.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class AuthDomainException extends DomainException {

    public AuthDomainException(String message) {
        super(message);
    }

    public AuthDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
