package com.xxm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TomcatStart extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TomcatStart.class, args);
        System.out.println("------------------>启动完毕<---------------------");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        System.out.println("------------------>外置 tomcat 启动完毕<---------------------");
        return builder.sources(TomcatStart.class);
    }
}
