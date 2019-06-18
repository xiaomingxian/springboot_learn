package com.xxm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
