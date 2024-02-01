package com.lisko.security.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */

@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtEntity {

    private JwtHeader header;
    private JwtBody body;
    private String sign;

    public JwtEntity() {
        header = new JwtHeader();
        body = new JwtBody();
    }

}
