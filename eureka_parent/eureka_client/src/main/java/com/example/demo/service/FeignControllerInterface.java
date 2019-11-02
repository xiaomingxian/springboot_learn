package com.example.demo.service;


import com.example.demo.service.exception.FeginClientFallBcakFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;
import java.util.Map;


//会自动生成代理类
@FeignClient(name = "SERVER",fallback= FeginClientFallBcakFactory.class)
public interface FeignControllerInterface {

    @RequestMapping("/learn/springclould_test")
    Map feignTest();
    @RequestMapping("/learn/hystrix/test")
    String hystixTest(Integer id);
}
