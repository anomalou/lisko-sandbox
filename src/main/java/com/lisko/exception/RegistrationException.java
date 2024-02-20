package com.lisko.exception;

import com.lisko.enums.ResponseCode;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
public class RegistrationException extends BaseRequestException{
    public RegistrationException() {
        super(ResponseCode.REG_FAILED, "Registration failed, try later");
    }
}
