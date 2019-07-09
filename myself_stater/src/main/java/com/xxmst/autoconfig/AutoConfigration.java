package com.xxmst.autoconfig;


import com.xxmst.config.HelloConfig;
import com.xxmst.controller.TestStController;
import com.xxmst.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//定义为配置类
@Configuration
//在web工程下条件下成立     https://www.jianshu.com/p/23f504713b94
@ConditionalOnWebApplication
//启用HelloProperties配置功能，并加入到IOC容器中[实际中可以整合自己需要的东西 eg:ahiro,swagger-ui...]
@EnableConfigurationProperties({HelloConfig.class})
//导入相关组件
//@Import({HelloService.class})
@ComponentScan(basePackageClasses = {TestStController.class, HelloService.class})
public class AutoConfigration {

    //可以只写这两个    --@EnableConfigurationProperties可以使用它替换以下的@Import
    //@Import({GeneralConfig.class,MybatisDaoConfig.class,ShiroConfig.class,Swagger2Config.class})
    //@ComponentScan(basePackageClasses ={GeneralController.class,GeneralServiceImpl.class, SysLogAspect.class} )
}
