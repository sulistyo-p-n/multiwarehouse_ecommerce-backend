package com.multiwarehouse.app.warehouse.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class WarehouseNotFoundException extends DomainException {
    public WarehouseNotFoundException(String message) {
        super(message);
    }
    public WarehouseNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
