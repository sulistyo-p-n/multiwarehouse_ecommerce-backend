package com.multiwarehouse.app.product.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class ProductImageDomainException extends DomainException {
    public ProductImageDomainException(String message) {
        super(message);
    }
    public ProductImageDomainException(String message, Throwable cause){
        super(message, cause);
    }
}
