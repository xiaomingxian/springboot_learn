package com.mybatis_plus.application;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//mybatis扫描使用
@MapperScan("com.mybatis_plus.dao")//或者：@Mapper
@SpringBootApplication
public class MyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class);
        System.out.println("<-------------------- mybatis_plus 启动 -------------------->");
    }
}
