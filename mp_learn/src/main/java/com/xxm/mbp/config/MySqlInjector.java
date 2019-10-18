package com.xxm.mbp.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MySqlInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {


        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        //添加自定义类方法创建类 mp也是这样
        methodList.add(new MethodCreate());
        //加入官方选装件
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !t.getColumn().equals("name")));
        //逻辑删除时自动填充
        //methodList.add(new LogicDeleteByIdWithFill());

        return methodList;
    }
}
