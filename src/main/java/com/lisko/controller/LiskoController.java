package com.lisko.controller;

import com.lisko.dto.response.DefaultResponse;
import com.lisko.service.LiskoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/lisko")
public class LiskoController {
    private final LiskoService liskoService;

    @GetMapping(value = "/get")
    public DefaultResponse justSimpleGetEndpoint(){
        return liskoService.justSimpleMethod();
    }
}
