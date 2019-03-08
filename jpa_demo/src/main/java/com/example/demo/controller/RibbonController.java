package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("useService")
    public Map useService() {
        Map body = new HashMap<>();
        String service_name = "SERVER";
        for (int x = 0; x < 10; x++) {
            //ribbon是客户端的负载均衡，在发送请求前，获取到服务实例
            ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://" + service_name + "/learn/springclould_test", Map.class);
            body = forEntity.getBody();
            System.out.println("-------->调用结果:" + body);
        }
        return body;
    }


}
