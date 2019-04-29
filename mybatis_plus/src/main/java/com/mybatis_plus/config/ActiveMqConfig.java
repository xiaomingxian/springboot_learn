package com.mybatis_plus.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.concurrent.*;

@Configuration
@EnableJms
public class ActiveMqConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("test.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("test.topic");
    }


    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("activemq-pool-%d").setDaemon(true).build();
    private static ExecutorService pool;

    static {
        //
        if (pool == null) {
            pool = new ThreadPoolExecutor(6, 6,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(6), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        }
    }

    @Resource
    private ConnectionFactory connectionFactory;
    //@Resource
    //ActiveMQConnectionFactory activeMQConnectionFactory;

    /**
     * 处理topic消息
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);//
        factory.setConnectionFactory(connectionFactory);
        factory.setTaskExecutor(pool);
        return factory;
    }

    /**
     * 处理queue消息
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);//
        factory.setConnectionFactory(connectionFactory);
        factory.setTaskExecutor(pool);
        return factory;
    }

}
