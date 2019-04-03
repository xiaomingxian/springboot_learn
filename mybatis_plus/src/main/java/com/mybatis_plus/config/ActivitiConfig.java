//package com.mybatis_plus.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * @author kingboy--KingBoyWorld@163.com
// * @date 2017/8/30 上午12:54
// * @desc Activiti的数据和事物配置.
// */
//@Configuration//声名为配置类，继承Activiti抽象配置类
//public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.activiti")
//    public DataSource primaryDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//
//        return druidDataSource;
//    }
//
//
//}