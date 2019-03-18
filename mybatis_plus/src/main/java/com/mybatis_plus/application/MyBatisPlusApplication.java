package com.mybatis_plus.application;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.mybatis_plus.controller", "com.mybatis_plus.service","com.mybatis_plus.config"})
//mybatis扫描使用
@MapperScan(basePackages = {"com.mybatis_plus.dao"})//或者：@Mapper
public class MyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class);
        System.out.println("<--------------------- mybatis_plus 启动 -------------------->");
    }
}


