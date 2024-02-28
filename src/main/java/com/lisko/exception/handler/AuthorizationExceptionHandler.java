package com.lisko.exception.handler;

import com.lisko.dto.response.DefaultResponse;
import com.lisko.enums.ResponseCode;
import com.lisko.exception.AuthorizationException;
import com.lisko.exception.BaseRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/28/24
 */

@RestControllerAdvice
public class AuthorizationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public DefaultResponse handleUserNotFound(UsernameNotFoundException exception, WebRequest request) {
        return DefaultResponse.builder()
                .code(ResponseCode.AUTH_FAILED.getCode())
                .codeDesc(ResponseCode.AUTH_FAILED.getDescription())
                .msg("Username not found: " + exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthorizationException.class)
    public DefaultResponse handleAuthorizationFail(BaseRequestException exception, WebRequest request) {
        return DefaultResponse.builder()
                .code(exception.getCode().getCode())
                .codeDesc(exception.getCode().getDescription())
                .msg(exception.getDesc())
                .build();
    }
}
