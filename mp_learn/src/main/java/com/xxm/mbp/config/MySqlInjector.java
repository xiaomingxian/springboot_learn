package com.xxm.mbp.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MySqlInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {


        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        //添加自定义类方法创建类 mp也是这样
        methodList.add(new MethodCreate());

        return methodList;
    }
}
