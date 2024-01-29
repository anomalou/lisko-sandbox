package com.lisko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@SpringBootApplication
@EnableJpaRepositories(value = "com.lisko.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
