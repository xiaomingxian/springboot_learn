package xxm.activemq_zhengshi;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;


@Configuration//使类初始化
@EnableJms
public class MyPojo {


    /**
     * 加@Bean注解来为我们需要提供服务的类进行注入，
     * 以往传统的springMVC就是在配置文件配置bean，
     * 指定class，properties之类的属性等等
     *
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("my-topic");
        //return null;
    }


    @Bean
    public Queue queue() {

        return new ActiveMQQueue("my-queue");

        //return null;
    }

    /** * JmsListener注解默认只接收queue消息,如果要接收topic消息,需要设置containerFactory */
    //@Bean
    //public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
    //    DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
    //    topicListenerContainer.setPubSubDomain(true);
    //    topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
    //    return topicListenerContainer;
    //}

}
