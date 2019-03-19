package com.mybatis_plus.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxm123
 * @since 2019-03-19
 */
@Data
@TableName("person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String name;


    @Override
    public String toString() {
        return "Person{" +
        ", username=" + username +
        ", password=" + password +
        ", name=" + name +
        "}";
    }
}
