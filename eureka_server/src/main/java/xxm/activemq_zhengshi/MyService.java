package xxm.activemq_zhengshi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service//应该写在实现类上面
public class MyService {


    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, Object msg) {
        //System.out.println("service-:" + msg);

        jmsTemplate.convertAndSend(destination, msg.toString());
    }

}
