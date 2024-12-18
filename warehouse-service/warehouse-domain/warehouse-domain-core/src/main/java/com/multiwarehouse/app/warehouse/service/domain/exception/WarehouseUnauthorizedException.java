package com.multiwarehouse.app.warehouse.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class WarehouseUnauthorizedException extends DomainException {
    public WarehouseUnauthorizedException(String message) {
        super(message);
    }
    public WarehouseUnauthorizedException(String message, Throwable cause){
        super(message, cause);
    }
}
