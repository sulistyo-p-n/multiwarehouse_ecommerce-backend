package com.multiwarehouse.app.product.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class ProductDomainException extends DomainException {
  public ProductDomainException(String message) {
    super(message);
  }
  public ProductDomainException(String message, Throwable cause){
    super(message, cause);
  }
}
