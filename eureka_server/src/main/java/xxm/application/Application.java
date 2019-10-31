package xxm.application;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;


@EnableCircuitBreaker
//既作为客户端又是服务端-配置多个实例来相互注册和自我注册-实现高可用
@EnableEurekaServer//来开启服务注册中心
@EnableDiscoveryClient//作为客户端??//服务发现
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
