package com.multiwarehouse.app.inventory.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class InventoryNotFoundException extends DomainException {
    public InventoryNotFoundException(String message) {
        super(message);
    }
    public InventoryNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
