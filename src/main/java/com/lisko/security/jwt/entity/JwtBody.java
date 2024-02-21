package com.lisko.security.jwt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtBody {

    private LocalDateTime exp;
    private String username;

}
