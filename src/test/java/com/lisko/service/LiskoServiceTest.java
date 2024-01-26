package com.lisko.service;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/26/24
 */

public class LiskoServiceTest {
    @Test
    public void testApache(){
        List<String> booleans = Arrays.asList("yes", "true", "TrUe", "TRUE", "tRUE", "True", "t");

        for (String value : booleans){
            Assertions.assertTrue(BooleanUtils.toBoolean(value));
        }
    }
}
