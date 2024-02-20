package com.lisko.security.jwt.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class JwtHeader {
    @JsonAlias("alg")
    @JsonProperty("alg")
    private String algorithm = "HS256";

    @JsonAlias("typ")
    @JsonProperty("typ")
    private String type = "JWT";
}
