package com.lisko.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class SignUpRequest implements Serializable {
    @NotBlank
    @Pattern(regexp = "\\w{1,20}")
    private String username;
    @NotBlank
    @Pattern(regexp = "[\\w\\W]{6,40}")
    private String password;
}
