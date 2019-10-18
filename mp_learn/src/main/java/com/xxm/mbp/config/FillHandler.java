package com.xxm.mbp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 填充处理器
 */
@Component
public class FillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //每个插入行为执行，此方法都会执行
        //System.out.println("-----------------");
        //setFieldValByName("","",metaObject);//通用
        boolean hasGetter = metaObject.hasGetter("createTime");
        if (hasGetter){
            //如果有这个属性才进行赋值
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //判断字段是否有值，如果有就不更新
        Object val = getFieldValByName("updateTime", metaObject);
        System.out.println("====>" + val);
        if (val == null){
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
