package com.lisko.exception;

import com.lisko.enums.ResponseCode;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
public class AuthorizationException extends BaseRequestException{
    public AuthorizationException(Object payload) {
        super(ResponseCode.AUTH_FAILED, "Authorization failed for " + payload);
    }
}
