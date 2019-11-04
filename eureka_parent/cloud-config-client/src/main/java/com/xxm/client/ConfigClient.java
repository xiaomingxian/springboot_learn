package com.xxm.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ConfigClient {


    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
        System.out.println("----------->>>启动成功<<<-----------");
    }
}
