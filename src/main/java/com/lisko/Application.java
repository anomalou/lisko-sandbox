package com.lisko;

import com.lisko.security.jwt.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@SpringBootApplication
@EnableJpaRepositories(value = "com.lisko.repository")
@EnableConfigurationProperties(JwtConfig.class)
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
