package xxm.application;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaServer//来开启服务注册中心
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"xxm.controller", "xxm.service"})
//mybatis扫描使用
@MapperScan("xxm.dao")
//activemq两种方式不可同时使用？？？？
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        System.out.println("<-------------------启动类------------------->");

    }
}
