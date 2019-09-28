package com.mybatis_plus.core.aop;

import java.lang.annotation.*;

/**
 * 定义系统日志注解
 *
 * @author xxm
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String function_id();

    String operate_type();

    String operate_content();
}
