package com.multiwarehouse.app.user.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class UserUnauthorizedException extends DomainException {

    public UserUnauthorizedException(String message) {
        super(message);
    }

    public UserUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
