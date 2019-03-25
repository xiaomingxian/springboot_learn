package com.mybatis_plus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class Swagger {
    @Bean
    public Docket baseApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //groupName 不能有中文  否则：  Can't read swagger JSON from http://localhost:8080/v2/api-docs?group=移动端接口文档
                .groupName("springBoot_mybatisPlus")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mybatis_plus.controller"))
                .paths(PathSelectors.any()).build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mybatisPlus案例")
                .description("mybatisPlus案例")
                .version("1.0").build();
    }
}