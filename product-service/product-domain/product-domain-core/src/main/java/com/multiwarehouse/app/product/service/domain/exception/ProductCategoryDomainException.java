package com.multiwarehouse.app.product.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class ProductCategoryDomainException extends DomainException {
    public ProductCategoryDomainException(String message) {
        super(message);
    }
    public ProductCategoryDomainException(String message, Throwable cause){
        super(message, cause);
    }
}
