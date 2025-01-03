package com.multiwarehouse.app.warehouse.service.domain.exception.handler;

import com.multiwarehouse.app.application.handler.ErrorDTO;
import com.multiwarehouse.app.application.handler.GlobalExceptionHandler;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseDomainException;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WarehouseGlobalExceptionHandler extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {WarehouseDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(WarehouseDomainException warehouseDomainException) {
        log.error(warehouseDomainException.getMessage(), warehouseDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(warehouseDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {WarehouseNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(WarehouseNotFoundException warehouseNotFoundException) {
        log.error(warehouseNotFoundException.getMessage(), warehouseNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(warehouseNotFoundException.getMessage())
                .build();
    }
}
