package com.lisko.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@RequiredArgsConstructor
@Getter
public enum ResponseCode {

    OK("0", "Everything ok"),
    AUTH_FAILED("100", "Authorization failed"),
    REG_FAILED("101", "Registration failed"),
    USER_NOT_FOUND("200", "User with provided username not found"),
    ROLE_NOT_FOUND("210", "Role with provided name not found"),
    FAIL("999", "Something wrong happened. I don't know what");

    private final String code;
    private final String description;
}
