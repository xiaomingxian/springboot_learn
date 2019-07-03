package com.xxmst.controller;

import com.xxmst.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("start")
public class TestController {
    @Autowired
    private HelloService helloService;

    @GetMapping("hello")
    public String hello() {
        return helloService.getMsg();
    }
}
