package com.example.demo;

import com.xxm.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "SERVER",configuration = MyRule.class)//针对SERVER这个微服务使用此种负载均衡算法
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
        System.out.printf("<------------------启动Jpa------------------>");
    }


    //微服务之间的通信--借助RestTemplate
    @Bean
    @LoadBalanced//开启客户端负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
