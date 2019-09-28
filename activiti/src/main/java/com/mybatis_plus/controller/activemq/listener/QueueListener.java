package com.mybatis_plus.controller.activemq.listener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class QueueListener {

    @JmsListener(destination = "test.queue", containerFactory = "queueListenerFactory")
    public void listener1(Message message) {
        System.out.println("================>springboot 队列监听器1 启动");
        if (message instanceof ActiveMQMapMessage) {
            ActiveMQMapMessage mapMessage = (ActiveMQMapMessage) message;
            System.out.println("--->队列 <1> 监听到的map类型信息：" + mapMessage.toString());
        }
        if (message instanceof ActiveMQTextMessage) {
            TextMessage textMessage = (ActiveMQTextMessage) message;
            System.out.println("--->队列 <1> 监听到的map类型信息：" + textMessage.toString());
        }
    }

    @JmsListener(destination = "test.queue", containerFactory = "queueListenerFactory")
    public void listener2(Message message) {
        System.out.println("================>springboot 队列监听器2 启动");
        if (message instanceof ActiveMQMapMessage) {
            MapMessage mapMessage = (ActiveMQMapMessage) message;
            System.out.println("--->队列 <2> 监听到的map类型信息：" + mapMessage.toString());
        }
        if (message instanceof ActiveMQTextMessage) {
            TextMessage textMessage = (ActiveMQTextMessage) message;
            System.out.println("--->队列 <2> 监听到的map类型信息：" + textMessage.toString());
        }
    }
}
