package com.xxm.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("test")
public class TestController {

    @Value("${properties.test}")
    private String properties;

    @GetMapping("properties")
    public String properties() {
        return properties;
    }

}
