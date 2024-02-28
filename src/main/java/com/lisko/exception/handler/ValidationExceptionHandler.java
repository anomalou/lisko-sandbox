package com.lisko.exception.handler;

import com.lisko.dto.response.DefaultResponse;
import com.lisko.enums.ResponseCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/28/24
 */

@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DefaultResponse response = DefaultResponse.builder()
                .code(ResponseCode.FAIL.getCode())
                .codeDesc(ResponseCode.FAIL.getDescription())
                .msg(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, response, headers, status, request);
    }
}
