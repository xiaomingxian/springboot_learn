package com.mybatis_plus.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Deployment implements Serializable {
    private String name;
    private String id;
    private String category;
    private Date deploymentTime;
    private String key;
    private String tenantId;
}
