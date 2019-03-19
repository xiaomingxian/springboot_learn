package xxm.controller;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Receive {


    @JmsListener(destination = "springboot-send")
    public void rec(String rec) {
        System.out.println("rec:" + rec);

    }

    @JmsListener(destination = "springboot-send-map")
    public void recmap(Map rec) {
        System.out.println("rec-map:" + rec);

    }


}
