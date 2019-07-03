package com.xxmst.service;

import com.xxmst.config.HelloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    private HelloConfig helloConfig;

    public String getMsg() {
        return "hello:" + helloConfig.getName() + "  " + helloConfig.getAge();
    }
}
