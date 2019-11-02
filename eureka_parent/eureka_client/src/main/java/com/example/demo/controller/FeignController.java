package com.example.demo.controller;


import com.example.demo.service.FeignControllerInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "Feign远程调用", description = "Feign远程调用")
@RestController
public class FeignController {

    @Autowired
    private FeignControllerInterface feignControllerInterface;

    @GetMapping("feign_test")
    @ApiOperation("feign调用测试")
    public Map feign_test() {

        Map map = feignControllerInterface.feignTest();
        return map;

    }

    @GetMapping("hysticTest")
    @ApiOperation("hysticTest异常统一处理")
    public String hysticTest(Integer id) {

        String s = feignControllerInterface.hystixTest(id);
        return s;

    }
}
