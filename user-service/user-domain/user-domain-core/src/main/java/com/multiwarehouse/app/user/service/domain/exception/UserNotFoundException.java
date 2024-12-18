package com.multiwarehouse.app.user.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
