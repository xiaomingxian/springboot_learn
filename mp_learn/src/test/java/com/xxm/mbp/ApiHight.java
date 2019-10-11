package com.xxm.mbp;

import com.xxm.mbp.dao.PersonMapper;
import com.xxm.mbp.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiHight {


    @Autowired
    private PersonMapper personMapper;

    @Test
    public void logicDel() {

        //执行的是update语句
        new Person().deleteById(1);

        //查询会忽略标记为删除的数据
        System.out.println("--->" + new Person().selectById(1));

        //update同理
        Person person = new Person();
        person.setId(1L);
        person.setName("xiu-gai");
        person.updateById();

        //排除逻辑删除字段的展示
        //@TableField(select = false)

    }

}
