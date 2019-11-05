package com.xxm.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Value("${properties.test}")
    private String properties;

    @GetMapping("pro")
    public String properties() {
        System.out.println("1111111111");
        return properties;
    }

    @GetMapping("test")
    public String test() {
        System.out.println("1212121");
        return "1111-----";
    }

}
