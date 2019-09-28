package com.xxm.mbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = {"com.xxm.mbp.dao"})
public class MpApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MpApp.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
        System.out.println("<=====================================> mybatisPlus 启动 <=====================================>");
    }
}
