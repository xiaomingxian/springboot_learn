package com.xxm.controller;

import com.xxmst.service.HelloService;
import com.xxmst.service.HelloService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private HelloService2 helloService2;

    @GetMapping("test")
    public String getTest() {
        String msg = helloService.getMsg();
        return "测试数据:" + msg;
    }

    @GetMapping("test2")
    public String getTest2() {
        String msg = helloService2.getMsg();
        return "测试数据2:" + msg;
    }
}
