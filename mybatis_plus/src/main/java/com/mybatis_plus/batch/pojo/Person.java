package com.mybatis_plus.batch.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Person {
    private int id;
    private String sex;
    private String name;
    private int age;
    private Date birthday;

}
