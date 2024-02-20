package com.lisko.exception;

import com.lisko.enums.ResponseCode;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
public class RoleNotFoundException extends BaseRequestException{
    public RoleNotFoundException(String name) {
        super(ResponseCode.ROLE_NOT_FOUND, name);
    }
}
