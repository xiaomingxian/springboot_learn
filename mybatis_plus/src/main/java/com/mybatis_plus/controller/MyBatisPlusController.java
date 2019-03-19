package com.mybatis_plus.controller;

import com.mybatis_plus.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "mybatisPlus测试", description = "mybatisPlus controller测试")
@RestController
@EnableAutoConfiguration
public class MyBatisPlusController {

    @Autowired
    private BaseService baseService;


    @ApiOperation("测试")
    @GetMapping("test")
    public String test() {
        return "-------- test --------";
    }

    @GetMapping("serviceTest")
    @ApiOperation("service测试")
    public String serviceTest() {
        return baseService.test();
    }

}
