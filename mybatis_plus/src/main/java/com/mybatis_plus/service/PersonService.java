package com.mybatis_plus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatis_plus.pojo.Person;

public interface PersonService extends IService<Person> {
    /**
     * 分页
     */
    Page<Person> queryPage(Page<Person> personPage);


}
