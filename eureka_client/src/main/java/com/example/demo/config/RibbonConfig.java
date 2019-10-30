package com.example.demo.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {


    @Bean
    public IRule iRule() {
        IRule iRule = null;
        //随机算法
        //iRule = new RandomRule();
        //重试算法----正常情况下轮训访问，有服务断了之后会尝试连接(连接N次失败后放弃)
        //iRule = new RetryRule();
        //轮训
        iRule = new RoundRobinRule();
        //先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数超过阈值的服务，对剩余的服务列表按照轮训策略进行访问
        //iRule=new AvailabilityFilteringRule();
        //根据平均响应时间计算所有服务的权重，相应时间越快服务权重越大被选中的概率越大//刚启动时统计信息不足，则使用轮训策略，等信息统计足时切换到权重策略
        //iRule=new WeightedResponseTimeRule();
        //会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的
        //iRule=new BestAvailableRule();
        //默认规则，复合判断server所在区域的性能和server的可用性选择服务器
        //iRule=new ZoneAvoidanceRule();

        return iRule;
    }

}
