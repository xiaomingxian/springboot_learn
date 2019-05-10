package com.example.demo.controller;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;
import java.util.Map;


//会自动生成代理类
@FeignClient(name = "SERVER")
public interface FeignControllerInterface {

    @RequestMapping("/learn/springclould_test")
    Map feignTest();
}
