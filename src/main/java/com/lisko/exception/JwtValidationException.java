package com.lisko.exception;

import com.lisko.enums.ResponseCode;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
public class JwtValidationException extends BaseRequestException{
    public JwtValidationException() {
        super(ResponseCode.AUTH_FAILED, "Jwt token not valid. Token secret not correct or it expired");
    }
}
