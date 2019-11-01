package com.example.demo.service.exception;

import com.example.demo.service.FeignControllerInterface;
import org.springframework.stereotype.Component;

import java.util.Map;


//@Component
//public class FeginClientFallBcakFactory implements FallbackFactory<FeignControllerInterface> {
//@Override
//public FeignControllerInterface create(Throwable throwable) {
//        return new FeignControllerInterface() {
//@Override
//public Map feignTest() {
//        return null;
//        }
//
//@Override
//public String hystixTest(Integer id) {
//        return "id过大：" + id;
//        }
//        };
//        }
//        }
@Component
public class FeginClientFallBcakFactory implements FeignControllerInterface {
    @Override
    public Map feignTest() {
        return null;
    }

    @Override
    public String hystixTest(Integer id) {
        //服务端关闭或者出现异常--会返回自定义信息
        return "id过大：" + id;
    }
}
