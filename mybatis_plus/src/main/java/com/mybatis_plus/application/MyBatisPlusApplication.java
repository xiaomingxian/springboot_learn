package com.mybatis_plus.application;


import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
//SecurityAutoConfiguration与activiti6.0.0冲突
@SpringBootApplication
@ComponentScan(basePackages = {"com.mybatis_plus.controller", "com.mybatis_plus.service", "com.mybatis_plus.config"})
//mybatis扫描使用
@MapperScan(basePackages = {"com.mybatis_plus.dao"})//或者：@Mapper
public class MyBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MyBatisPlusApplication.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
        System.out.println("<----------------------> mybatis_plus 启动 <--------------------->");
    }

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    /**
     * 设置匹配*.do后缀请求
     *
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
        //servletServletRegistrationBean.addUrlMappings("*.do");
        return servletServletRegistrationBean;
    }

}


