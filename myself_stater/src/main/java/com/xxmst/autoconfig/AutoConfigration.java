package com.xxmst.autoconfig;


import com.xxmst.config.HelloConfig;
import com.xxmst.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//定义为配置类
@Configuration
//在web工程下条件下成立     https://www.jianshu.com/p/23f504713b94
@ConditionalOnWebApplication
//启用HelloProperties配置功能，并加入到IOC容器中[实际中可以整合自己需要的东西 eg:ahiro,swagger-ui...]
@EnableConfigurationProperties({HelloConfig.class})
//导入相关组件
@Import({HelloService.class})
public class AutoConfigration {
}
