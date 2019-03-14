package com.mybatis_plus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "mybatisPlus", description = "mybatisPlus controller")
@RestController
public class MyBatisPlusController {

    @ApiOperation("测试")
    @GetMapping("test")
    public String test() {
        return "-------- test --------";
    }
}
