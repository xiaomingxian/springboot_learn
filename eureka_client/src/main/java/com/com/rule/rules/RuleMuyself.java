package com.com.rule.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 自定义算法--基于随机算法修改
 * <p>
 * 每个微服务-服务5次
 */
public class RuleMuyself extends AbstractLoadBalancerRule {

    int index;
    int count = 0;
    int max = 5;

    /**
     * Randomly choose from all living servers
     */
    //@edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE")
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        //哪一台服务
        Server server = null;

        while (server == null) {
            //线程是否被打断
            if (Thread.interrupted()) {
                return null;
            }
            //活着的机器
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            //获取随机索引
            //if (count == 0)
            //    index = chooseRandomInt(serverCount);
            //else if (count >= max)
            //    count = 0;


            server = upList.get(index);
            System.out.println( "--------------:" + server.getHostPort());
            count++;
            if (count % max == 0) index++;
            if (index == 3) index = 0;//因为示例有三个服务-为了防止索引越界


            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}