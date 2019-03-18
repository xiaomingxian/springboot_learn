package xxm.activemq_zhengshi;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @JmsListener(destination = "my-queue")
    public void queue(String text) {
        System.out.println("queue消费:" + text);

    }

    @JmsListener(destination = "my-topic",containerFactory = "")
    public void topic(String text) {
        System.out.println("topic-监听");
        System.out.println("topic消费:" + text);

    }


}
