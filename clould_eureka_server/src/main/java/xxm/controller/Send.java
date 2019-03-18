package xxm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Send {

    @Autowired
    private JmsTemplate jmsTemplate;


    @RequestMapping("send")
    public void send(String msg) {

        jmsTemplate.convertAndSend("springboot-send", msg);


    }

    @RequestMapping("sendMap")
    public void sendMap() {

        HashMap map = new HashMap();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");

        jmsTemplate.convertAndSend("springboot-send-map", map);


    }


}
