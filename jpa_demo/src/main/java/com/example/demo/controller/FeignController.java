package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FeignController {

    @Autowired
    private FeignControllerInterface feignControllerInterface;

    @RequestMapping("feign_test")

    public Map feign_test() {

        Map map = feignControllerInterface.feignTest();
        return map;

    }
}
