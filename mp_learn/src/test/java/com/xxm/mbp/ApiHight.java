package com.xxm.mbp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxm.mbp.config.MybatisPlusConfig;
import com.xxm.mbp.dao.PersonMapper;
import com.xxm.mbp.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

        //自定义sql不会加入逻辑删除过滤
    }

    @Test
    public void fill(){
        //Person person = new Person();
        //person.setName("填充测试");
        //person.setUsername("tom");
        //person.setPassword("11111");
        ////person.insert();
        //
        //
        //person.setUsername("更新信息");
        //person.setId(7L);
        //person.updateById();


        //User user = new User();
        //user.setName("自动填充时间测试");
        //user.setPassword("123456");
        //user.insert();
    }

    @Test
    public void leGuanSuo() {
        int version = 2;
        Person person = new Person();
        person.setId(7L);
        person.setUsername("版本号测试");
        person.setUsername("banben");
        person.setUsername("版本");
        person.setVersion(version);
        //person.updateById();


        //复用mapper测试----复用会传两个版本号
        //DEBUG==>  Preparing: UPDATE person SET update_time=?, version=?, username=? WHERE deleted=0 AND (id = ? AND version = ? AND version = ?)
        //DEBUG==> Parameters: 2019-10-14T22:43:07.691(LocalDateTime), 4(Integer), 版本3(String), 7(Long), 2(Integer), 3(Integer)
        //DEBUG<==    Updates: 0
        int version2 = 2;
        Person person2 = new Person();
        person2.setId(7L);
        person2.setUsername("版本号测试2");
        person2.setUsername("banben2");
        person2.setUsername("版本2");
        person2.setVersion(version2);

        QueryWrapper<Person> personQueryWrapper = new QueryWrapper<>();
        personQueryWrapper.eq("id", 7L);
        personMapper.update(person2, personQueryWrapper);

        int version3 = 3;
        Person person3 = new Person();
        person3.setId(7L);
        person3.setUsername("版本号测试3");
        person3.setUsername("banben3");
        person3.setUsername("版本3");
        person3.setVersion(version3);
        personMapper.update(person3, personQueryWrapper);
    }


    /**
     * 多租户
     */
    @Test
    public void duoZuHu() {
        List<Person> people = personMapper.selectList(null);
        people.stream().forEach(System.out::println);

        System.out.println("--------------->自定义注解");
        personMapper.selectFilter().stream().forEach(System.out::println);
    }

    /**
     * 动态表明
     */
    @Test
    public void dymicTableName() {

        MybatisPlusConfig.TABLE_NAME.set("person_2019-10-16");
        //加了 @SqlParser(filter = true) 动态表名也会失效
        List<Person> people = personMapper.selectList(null);
        people.stream().forEach(System.out::println);
    }

    /**
     * sql注入器
     * 1 创建定义方法的类
     * 2 创建注入器
     * 3 在mapper中加入自定义方法
     */
    @Test
    public void sqlInter() {

        Integer count = personMapper.selectCountTotal();

        System.out.println("------>"+count);
    }

}
