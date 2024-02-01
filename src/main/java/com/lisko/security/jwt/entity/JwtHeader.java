package com.lisko.security.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtHeader {
    private String algorithm = "HS256";
    private String type = "JWT";

    @Override
    public String toString() {
        return "{\"alg\": \"" + algorithm + "\", \"typ\": \"" + type + "\"}";
    }
}
