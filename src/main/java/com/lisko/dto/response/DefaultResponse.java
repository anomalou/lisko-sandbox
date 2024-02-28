package com.lisko.dto.response;

import com.lisko.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse {
    private String code;
    private String codeDesc;
    private Object msg;
}
