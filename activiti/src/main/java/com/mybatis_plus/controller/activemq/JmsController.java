package com.mybatis_plus.controller.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("jms")
public class JmsController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    private ExecutorService pool = Executors.newFixedThreadPool(5);

    @GetMapping("queue")
    public String queue() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.submit(() -> {
                //runable--也可以callable
                HashMap<String, String> map = new HashMap<>();
                String name = Thread.currentThread().getName();
                map.put(name, "springboot-队列消息" + finalI);

                //jmsTemplate.convertAndSend("test.queue", map);
                jmsMessagingTemplate.convertAndSend(queue, map);
            });
        }

        return "队列消息生产成功";
    }

    @GetMapping("topic")
    public String topic() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.submit(() -> {
                //runable--也可以callable
                HashMap<String, String> map = new HashMap<>();
                String name = Thread.currentThread().getName();
                map.put(name, "springboot-topic消息" + finalI);

                //jmsTemplate.convertAndSend("test.queue", map);
                jmsMessagingTemplate.convertAndSend(topic, map.toString());
            });
        }

        return "topic消息生产成功";
    }


}
