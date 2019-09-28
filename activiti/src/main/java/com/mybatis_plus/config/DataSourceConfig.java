package com.mybatis_plus.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean("dataSource")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource activitiDataSource() {

        return DruidDataSourceBuilder.create().build();
    }

    @Bean("platformTransactionManager")
    public PlatformTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource) {
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource);
        return platformTransactionManager;
    }


}
