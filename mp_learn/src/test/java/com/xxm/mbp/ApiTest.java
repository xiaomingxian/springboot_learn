package com.xxm.mbp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xxm.mbp.dao.UserMapper;
import com.xxm.mbp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    /**/
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        List list = Arrays.asList(1, 2, 3, 4, 5);
        List list1 = userMapper.selectBatchIds(list);
        System.out.println(list1);

        HashMap<String, Object> map = new HashMap<>();
        //以数据库字段为准
        map.put("username","tom");
        userMapper.selectByMap(map);
    }

    /**
     * Wrapper
     */
    @Test
    public void wrapper(){
        //create method1
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //create method2
        QueryWrapper<User> query = Wrappers.query();

        //数据库字段
        wrapper.like("username", "o");

        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);

        //名字包含o,id在0到3之间，密码不为空
        query.like("username", "o").between("uid", 0, 3).isNotNull("password");
        List<User> users1 = userMapper.selectList(query);
        users1.stream().forEach(System.out::println);


    }

    @Test
    public void orderTest() {
        //    多字段排序
        QueryWrapper<User> query = Wrappers.query();
        query.orderByDesc("password").orderByDesc("uid");
        List<User> users = userMapper.selectList(query);
        users.stream().forEach(System.out::println);

    }

    /**
     * 子查询
     */
    @Test
    public void childQuery() {
        QueryWrapper<User> query = Wrappers.query();
        //select * from user where username='tom' and uid in (select uid from user wher uid in (0,1,2,3,4))
        query.apply("username={0}", "tom").inSql("uid", "select uid from user where uid in (0,1,2,3,4)");

        List<User> users = userMapper.selectList(query);
        users.stream().forEach(System.out::println);

    }

    /**
     * lambda
     */
    @Test
    public void lambda() {
        QueryWrapper<User> query = Wrappers.query();
        //select  * from user where username like 'to%' and (password>1234 or uid is not null)
        query.likeRight("username", "to").and(wp -> wp.lt("password", "1234").or().isNotNull("uid"));
        userMapper.selectList(query).stream().forEach(System.out::println);

    }




}
