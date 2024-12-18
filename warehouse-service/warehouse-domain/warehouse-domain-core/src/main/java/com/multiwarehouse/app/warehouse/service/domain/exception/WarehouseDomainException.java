package com.multiwarehouse.app.warehouse.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class WarehouseDomainException extends DomainException {

    public WarehouseDomainException(String message) {
        super(message);
    }

    public WarehouseDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
