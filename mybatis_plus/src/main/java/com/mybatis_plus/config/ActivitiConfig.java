package com.mybatis_plus.config;

import com.alibaba.druid.pool.DruidDataSource;
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
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/8/30 上午12:54
 * @desc Activiti的数据和事物配置.
 */
@Configuration//声名为配置类，继承Activiti抽象配置类
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
    //
    //@Bean(name = "activitiDataSource")
    //@Primary
    //@ConfigurationProperties(prefix = "spring.activiti")
    //public DataSource primaryDataSource() {
    //    return new com.alibaba.druid.pool.DruidDataSource();
    //}
    //
    //
    //@Bean(name = "activitiTransactionManager")
    //@Primary
    //public PlatformTransactionManager activitiTransactionManager(@Qualifier("activitiDataSource") DataSource activitiDataSource) {
    //    return new DataSourceTransactionManager(activitiDataSource);
    //}

    ////注入数据源和事务管理器
    //@Bean
    //public SpringProcessEngineConfiguration springProcessEngineConfiguration(
    //        @Qualifier("activitiDataSource") DataSource activitiDataSource,
    //        @Qualifier("activitiTransactionManager") PlatformTransactionManager activitiTransactionManager,
    //        SpringAsyncExecutor springAsyncExecutor
    //) throws IOException {
    //    //数据源，事务
    //    SpringProcessEngineConfiguration springProcessEngineConfiguration = this.baseSpringProcessEngineConfiguration(activitiDataSource, activitiTransactionManager, springAsyncExecutor);
    //
    //    return springProcessEngineConfiguration;
    //}


    //注入数据源和事务管理器
    @Bean
    @ConfigurationProperties(prefix = "spring.activiti")
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            SpringAsyncExecutor springAsyncExecutor
    ) throws IOException {
        DruidDataSource activitiDataSource = new DruidDataSource();
        DataSourceTransactionManager activitiTransactionManager = new DataSourceTransactionManager(activitiDataSource);
        //数据源，事务
        SpringProcessEngineConfiguration springProcessEngineConfiguration = this.baseSpringProcessEngineConfiguration(activitiDataSource, activitiTransactionManager, springAsyncExecutor);

        return springProcessEngineConfiguration;
    }
}