package com.xxmst.service;

import com.xxmst.config.HelloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService2 {
    @Autowired
    private HelloConfig helloConfig;

    public String getMsg() {
        return "hello22222222222222222:" + helloConfig.getName() + "  " + helloConfig.getAge();
    }
}
