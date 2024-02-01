package com.lisko.exception;

import com.lisko.enums.ResponseCode;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
public class JwtCreationException extends BaseRequestException{
    public JwtCreationException() {
        super(ResponseCode.AUTH_FAILED, "JWT can't be created");
    }
}
