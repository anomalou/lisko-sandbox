package com.lisko.exception;

import com.lisko.enums.ResponseCode;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
public class JwtParserException extends BaseRequestException{
    public JwtParserException() {
        super(ResponseCode.AUTH_FAILED, "Jwt can't be parsed");
    }
}
