package com.mybatis_plus.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author xiaoming.xian
 * @desc Activiti的数据和事物配置
 */
@Configuration//声名为配置类，继承Activiti抽象配置类
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Bean("activitiDataSource")
    @ConfigurationProperties("spring.activiti")
    public DataSource activitiDataSource() {

        return DruidDataSourceBuilder.create().build();
    }

    @Bean("activitiPlatformTransactionManager")
    public PlatformTransactionManager txManager(@Qualifier("activitiDataSource") DataSource activitiDataSource) {
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(activitiDataSource);
        return platformTransactionManager;
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            @Qualifier("activitiDataSource") DataSource activitiDataSource,
            @Qualifier("activitiPlatformTransactionManager") PlatformTransactionManager activitiPlatformTransactionManager,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {

        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
        //数据源
        springProcessEngineConfiguration.setDataSource(activitiDataSource);
        //事务
        springProcessEngineConfiguration.setTransactionManager(activitiPlatformTransactionManager);
        //自动建表
        springProcessEngineConfiguration.setDatabaseSchemaUpdate("true");

        return springProcessEngineConfiguration;
    }

}
