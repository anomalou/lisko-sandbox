package com.lisko.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "lisko.jwt")
public class JwtConfig {
    private String secret;
    private Long ttl;
}
