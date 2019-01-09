package xxm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取配置信息测试
 */
@RestController
//方式三:注解+setter
@ConfigurationProperties(prefix = "person")
public class GetPropertiesController {

    //方式一
    @Autowired
    private Environment environment;

    //方式二
    @Value("${name}")
    private String name_;

    //方式三:
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @RequestMapping("/getPropertiesTest")
    public String test() {

        return "方式一：url" + environment.getProperty("url") +
                "<br/>方式二：name:" + name_ +
                "<br/>方式三: name:" + name + " age:" + age +
                "";

    }


}
