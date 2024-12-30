package com.multiwarehouse.app.inventory.service.domain.exception.handler;

import com.multiwarehouse.app.application.handler.ErrorDTO;
import com.multiwarehouse.app.application.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class InventoryExceptionHandler extends GlobalExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler(value = {ProductDomainException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDTO handleException(ProductDomainException productDomainException) {
//        log.error(productDomainException.getMessage(), productDomainException);
//        return ErrorDTO.builder()
//                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                .message(productDomainException.getMessage())
//                .build();
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = {ProductNotFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDTO handleException(ProductNotFoundException productNotFoundException) {
//        log.error(productNotFoundException.getMessage(), productNotFoundException);
//        return ErrorDTO.builder()
//                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
//                .message(productNotFoundException.getMessage())
//                .build();
//    }
}
