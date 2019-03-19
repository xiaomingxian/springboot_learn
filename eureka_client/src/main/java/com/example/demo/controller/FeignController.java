package com.example.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
