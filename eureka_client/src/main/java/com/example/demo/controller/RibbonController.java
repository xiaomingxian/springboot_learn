package com.example.demo.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Api(value = "ribbon远程调用", description = "ribbon远程调用")
@RestController
public class RibbonController {

    /**
     * restTemplate：提供多种便捷访问远程Http服务的方法，是一种访问restful服务模版类,是spring提供的用于访问Rest服务的客户端模版工具集
     * 对rest调用做了封装
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "useService",method = RequestMethod.GET)
    public Map useService() {
        Map body = new HashMap<>();
        String service_name = "SERVER";//server中的spring.application.name
        for (int x = 0; x < 10; x++) {
            //ribbon是客户端的负载均衡，在发送请求前，获取到服务实例
            ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://" + service_name + "/learn/springclould_test", Map.class);
            body = forEntity.getBody();
            System.out.println("-------->调用结果:" + body);
        }
        return body;
    }


    @GetMapping("getInstances")
    public ResponseEntity<Map> getInstances() {
        String service_name = "SERVER";//server中的spring.application.name

        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://" + service_name + "/learn/instances", Map.class);

        return forEntity;
    }


}
