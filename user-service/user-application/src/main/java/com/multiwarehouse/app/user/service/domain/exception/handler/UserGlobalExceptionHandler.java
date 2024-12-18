package com.multiwarehouse.app.user.service.domain.exception.handler;

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
public class UserGlobalExceptionHandler extends GlobalExceptionHandler {
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
