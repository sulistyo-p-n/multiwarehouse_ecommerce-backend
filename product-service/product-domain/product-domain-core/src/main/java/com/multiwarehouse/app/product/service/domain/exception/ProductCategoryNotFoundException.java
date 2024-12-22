package com.multiwarehouse.app.product.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class ProductCategoryNotFoundException extends DomainException {
    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
    public ProductCategoryNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
