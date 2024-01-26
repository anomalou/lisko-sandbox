package com.lisko.service.impl;

import com.lisko.dto.DefaultResponse;
import com.lisko.enums.ResponseCode;
import com.lisko.service.LiskoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@Service
@RequiredArgsConstructor
public class LiskoServiceImpl implements LiskoService {
    @Override
    public DefaultResponse justSimpleMethod() {
        DefaultResponse defaultResponse = DefaultResponse.builder()
                .code(ResponseCode.OK)
                .build();

        return defaultResponse;
    }
}
