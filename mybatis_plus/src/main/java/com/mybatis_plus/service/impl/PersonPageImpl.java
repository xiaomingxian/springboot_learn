package com.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatis_plus.dao.PersonMapper;
import com.mybatis_plus.pojo.Person;
import com.mybatis_plus.service.PersonService;
import org.springframework.stereotype.Service;


@Service
public class PersonPageImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    @Override
    public Page<Person> queryPage(Page<Person> personPage) {
        //return personPage.setRecords(this.baseMapper.queryPage());
        return null;
    }
}
