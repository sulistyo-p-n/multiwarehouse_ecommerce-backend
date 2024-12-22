package com.multiwarehouse.app.product.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class ProductNotFoundException extends DomainException {
  public ProductNotFoundException(String message) {
    super(message);
  }
  public ProductNotFoundException(String message, Throwable cause){
    super(message, cause);
  }
}
