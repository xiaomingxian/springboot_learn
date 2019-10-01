package com.xxm.mbp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.Map;

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

    /**
     * update
     */
    @Test
    public void update() {
        User user = new User();
        user.setName("Jemmi");
        user.setPassword("123456");

        QueryWrapper<User> query = Wrappers.query();
        query.eq("username", "update-test");
        //wrapper为null更新所有数据
        userMapper.update(user, query);

    }

    @Test
    public void selectOne() {
        QueryWrapper<User> query = Wrappers.query();
        //如果结果非唯一
        query.eq("username", "Jemmi").last("limit 1");
        userMapper.selectOne(query);

    }

    @Test
    public void selectTest() {
        QueryWrapper<User> query = Wrappers.query();
        query.eq("username", "Jemmi");
        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        maps.stream().forEach(System.out::println);

        System.out.println("--------");
        QueryWrapper<User> query1 = Wrappers.query();
        query.eq("username", "tom");
        //只返回第一个字段的值--默认排序？
        List<Object> objects = userMapper.selectObjs(query1);
        System.out.println("~~~~~" + objects);
        objects.stream().forEach(System.out::println);
    }


    @Test
    public void pageTest() {
        IPage<User> page = new Page<>();
        page.setCurrent(2);
        page.setSize(5);

        IPage<User> userIPage = userMapper.selectPage(page, null);
        userIPage.getRecords().stream().forEach(System.out::println);
        System.out.println("---------- pageMap ------------");
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        mapIPage.getRecords().stream().forEach(System.out::println);

    }

    @Test
    public void other() {





    }




}
