package xxm.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hystrix")
public class HystrixController {


    @GetMapping("test")
    @HystrixCommand(fallbackMethod = "exceptionMethod")
    public String test(Integer id) {
        if (id > 100) {
            throw new RuntimeException("模拟异常");
        }
        return id + "";
    }

    public String exceptionMethod(Integer id){
        return "出错处理的方法的返回值：id数值过大";
    }

}
