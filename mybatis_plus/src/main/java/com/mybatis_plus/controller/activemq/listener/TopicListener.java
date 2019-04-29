package com.mybatis_plus.controller.activemq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {

    @JmsListener(destination = "test.topic", containerFactory = "topicListenerFactory")
    public void readActiveTopic(String message) {
        System.out.println("------>springboot topic监听 <1>：" + message);
    }


    @JmsListener(destination = "test.topic", containerFactory = "topicListenerFactory")
    public void readActiveTopic2(String message) {
        System.out.println("------>springboot topic监听 <2>：" + message);
    }


}
