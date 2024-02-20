package com.lisko.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
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
public class SignInRequest implements Serializable {
    private String username;
    private String password;
}
