package com.multiwarehouse.app.product.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class ProductImageNotFoundException extends DomainException {
    public ProductImageNotFoundException(String message) {
        super(message);
    }
    public ProductImageNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
