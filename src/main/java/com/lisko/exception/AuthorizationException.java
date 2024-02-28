package com.lisko.exception;

import com.lisko.enums.ResponseCode;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
public class AuthorizationException extends BaseRequestException{
    public AuthorizationException(@Nullable Object payload) {
        super(ResponseCode.AUTH_FAILED, !Objects.isNull(payload) ? "Authorization failed for " + payload : null);
    }
}
