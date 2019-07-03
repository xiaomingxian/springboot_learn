package com.xxm.controller;

import com.xxmst.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private HelloService helloService;

    @GetMapping("test")
    public String getTest() {
        String msg = helloService.getMsg();
        return "测试数据:" + msg;
    }
}
