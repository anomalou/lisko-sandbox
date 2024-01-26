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
    FAIL(999, "Something wrong happened. I don't know what");

    private final int code;
    private final String description;
}
