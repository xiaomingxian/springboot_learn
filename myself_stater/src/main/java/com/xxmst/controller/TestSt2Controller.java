package com.xxmst.controller;

import com.xxmst.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("start2")
public class TestSt2Controller {
    @Autowired
    private HelloService helloService;

    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "s==============>2";
    }

    @GetMapping("page")
    public String getPage() {
        return "/hello.html";
    }

}
