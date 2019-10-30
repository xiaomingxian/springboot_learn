package com.xxm.rule;

import com.com.rule.rules.RuleMuyself;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡规则
 * 官方文档：自定义规则配置类不能放在 @ComponentScan所扫描的当前包及其子包下，否则自定义的这个配置类就会被所有ribbon客户端共享
 * 就达不到特殊化定制的目的
 */

@Configuration
public class MyRule {

    @Bean
    public IRule iRule() {
        return new RuleMuyself();
    }

}
