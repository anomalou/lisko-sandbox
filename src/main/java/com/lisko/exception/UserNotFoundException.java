package com.lisko.exception;

import com.lisko.enums.ResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/29/24
 */
@Getter
public class UserNotFoundException extends BaseRequestException{
    private final String username;
    public UserNotFoundException(String username) {
        super(ResponseCode.USER_NOT_FOUND, username);
        this.username = username;
    }
}
