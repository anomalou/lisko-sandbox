package com.lisko.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@RequiredArgsConstructor
@Getter
public enum ResponseCode {

    OK(0, "Everything ok"),
    AUTH_FAILED(100, "Authorization failed"),
    USER_NOT_FOUND(200, "User with provided username not found"),
    FAIL(999, "Something wrong happened. I don't know what");

    private final int code;
    private final String description;
}
