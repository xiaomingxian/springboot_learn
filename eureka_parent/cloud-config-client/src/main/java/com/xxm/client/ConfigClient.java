package com.xxm.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;

@SpringBootApplication
public class ConfigClient {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(ConfigClient.class, args);
        ConfigurableEnvironment env = run.getEnvironment();

        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        System.out.println("----------->>>Client 启动成功 "+ip+":"+port+"/"+path+"<<<-----------");
    }
}
