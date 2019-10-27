package com.example.demo.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {


    @Bean
    public IRule iRule() {
        IRule iRule = null;
        //随机算法
        //iRule = new RandomRule();
        //重试算法
        //iRule = new RetryRule();
        //轮训
        iRule = new RoundRobinRule();

        return iRule;
    }

}
