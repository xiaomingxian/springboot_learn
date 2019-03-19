package com.mybatis_plus.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mybatis_plus.pojo.Person;
import com.mybatis_plus.service.PersonService;
import org.apache.velocity.runtime.directive.MacroParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xxm123
 * @since 2019-03-19
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("queryPage")
    public Page queryAll(Person person) {
        //条件封装
        EntityWrapper<Person> personEntityWrapper = new EntityWrapper<>();
        personEntityWrapper.where("name={0}", person.getName()).andNew("password>{0}", person.getPassword()).orderBy("password");
        //分页封装
        Page<Person> personPage = new Page<>(1, 3);
        Page<Person> personPage1 = personService.selectPage(personPage, personEntityWrapper);
        return personPage1;
    }


}

