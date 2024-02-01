package com.lisko.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "lisko.jwt")
public class JwtConfig {
    private String secret;
}
