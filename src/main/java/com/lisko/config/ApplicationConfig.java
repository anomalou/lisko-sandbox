package com.lisko.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "lisko")
public class ApplicationConfig {
    private String baseRegRole;
}
