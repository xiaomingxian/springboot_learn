package xxm.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxm.pojo.Person;
import xxm.service.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {

    //springboot会自动创建redis模版
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private PersonService personService;

    @RequestMapping("queryById")
    public Map queryById(String id) {
        HashMap map = new HashMap<Object, Object>();

        ArrayList data = new ArrayList();

        String value = redisTemplate.boundValueOps("person_" + id).get();
        if (StringUtils.isBlank(value)) {
            //查询数据库
            Person person = personService.queryById(id);
            if (person != null) {
                redisTemplate.boundValueOps("person_" + id).set(person.getName());
                map.put("log", "查询数据库并存储至redis");
                data.add(person.getName());
            }
        } else {
            map.put("log", "从redis中获取值");
            data.add(value);
        }
        map.put("data", data);

        return map;
    }


    @RequestMapping("pipeLineTest")
    public void  pipeLineTest(){
//        redisTemplate.pip


    }


}
