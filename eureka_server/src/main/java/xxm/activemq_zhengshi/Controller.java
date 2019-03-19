package xxm.activemq_zhengshi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;


@RestController
public class Controller {


    @Autowired
    private Topic topic;

    @Autowired
    private Queue queue;

    @Autowired
    private MyService myService;

    @RequestMapping("test2")
    public void test() {

        System.out.println("-----------" + queue + "   :  " + topic);
    }

    @RequestMapping("send-queue/{msg}")
    public void sendQueue(@PathVariable("msg") String msg) {
        //System.out.println("controller-queue:" + msg + "  -->" + queue);
        myService.sendMessage(queue, msg);

    }

    @RequestMapping("send-topic/{msg}")
    public void sendTopic(@PathVariable("msg") String msg) {
        System.out.println("topic:" + msg);
        myService.sendMessage(topic, msg);


    }

}
