package com.lisko.exception;

import com.lisko.enums.ResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/29/24
 */
@Getter
@RequiredArgsConstructor
public class BaseRequestException extends RuntimeException{
    private final ResponseCode code;
    private final Object desc;
}
