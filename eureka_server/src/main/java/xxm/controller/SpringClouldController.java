package xxm.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SpringClouldController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("springclould_test")
    public Map test() {
        HashMap<Object, Object> map = new HashMap();
        System.out.printf("------------>" + client.description());
        map.put("springclould_test", "test");
        return map;
    }

    @GetMapping("instances")
    public DiscoveryClient instances() {
        List<String> services = discoveryClient.getServices();
        services.stream().forEach(i -> {
            System.out.println("--------->" + i);
        });

        return discoveryClient;
    }

}
