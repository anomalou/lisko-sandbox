package com.lisko.dto.response;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse implements Serializable {
    private String token;
}
