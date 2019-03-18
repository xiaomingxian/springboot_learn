package com.mybatis_plus.service.impl;

import com.mybatis_plus.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {


    @Override
    public String test() {
        System.out.println("------------->base_test<------------------");
        return "----------->base_test<------------";
    }
}
