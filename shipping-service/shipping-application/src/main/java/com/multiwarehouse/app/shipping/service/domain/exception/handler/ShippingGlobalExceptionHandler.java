package com.multiwarehouse.app.shipping.service.domain.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ShippingGlobalExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler(value = {UserDomainException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDTO handleException(UserDomainException userDomainException) {
//        log.error(userDomainException.getMessage(), userDomainException);
//        return ErrorDTO.builder()
//                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                .message(userDomainException.getMessage())
//                .build();
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = {UserNotFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDTO handleException(UserNotFoundException userNotFoundException) {
//        log.error(userNotFoundException.getMessage(), userNotFoundException);
//        return ErrorDTO.builder()
//                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
//                .message(userNotFoundException.getMessage())
//                .build();
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = {UserUnauthorizedException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorDTO handleException(UserUnauthorizedException userUnauthorizedException) {
//        log.error(userUnauthorizedException.getMessage(), userUnauthorizedException);
//        return ErrorDTO.builder()
//                .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
//                .message(userUnauthorizedException.getMessage())
//                .build();
//    }
}
