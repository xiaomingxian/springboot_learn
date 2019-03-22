//package com.mybatis_plus.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ActivitiDataSource {
//    @Bean(name = "activitiDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "activiti")
//    public DataSource primaryDataSource() {
//        return new com.alibaba.druid.pool.DruidDataSource();
//    }
//
//}