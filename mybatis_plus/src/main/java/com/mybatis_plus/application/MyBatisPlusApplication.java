package com.mybatis_plus.application;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//既作为客户端又是服务端-配置多个实例来相互注册和自我注册-实现高可用
@EnableDiscoveryClient//作为客户端
@SpringBootApplication
//mybatis扫描使用
@MapperScan("xxm.dao")
public class MyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class);
        System.out.println("<-------------------- mybatis_plus 启动 -------------------->");
    }
}
