package com.xxm.mbp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
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
    public void selectPartOfColumn() {

        QueryWrapper<User> query = Wrappers.query();
        //method1 正向选择
        query.select("uid", "username");
        List<User> users = userMapper.selectList(query);
        users.stream().forEach(System.out::println);
        //method2 逆向排除
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //主键无法排除？
        wrapper.select(User.class, info -> !info.getColumn().equals("password") && !info.getColumn().equals("username"));
        userMapper.selectList(wrapper).stream().forEach(System.out::println);

    }

    @Test
    public void conditionTest() {
        //lt/eq 等方法内部都有重载 第一个参数是boolean类型(condition[true表示加入查询条件/反之相反])
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String name = "tom";
        //String name = "";
        wrapper.eq(StringUtils.isNotEmpty(name), "username", name);
        userMapper.selectList(wrapper).stream().forEach(System.out::println);
    }

    @Test
    public void entityQuery() {
        //    @TableField(condition = SqlCondition.LIKE)//指定字段查询属性//需要自定义直接 condition = "xxxx"
        //自动控制过滤
        User user = new User();
        user.setName("tom");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        userMapper.selectList(wrapper).stream().forEach(System.out::println);
    }

    @Test
    public void allEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "tom");
        map.put("uid", null);

        //wrapper.allEq(map);
        wrapper.allEq(map, false);//过滤null
        //字段过滤
        wrapper.allEq((k, v) -> !k.equals("username"), map);

        userMapper.selectList(wrapper).stream().forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        //别名
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(uid) max_val", "min(password) min_val").groupBy("uid");
        userMapper.selectMaps(wrapper).stream().forEach(System.out::println);
    }

    /**
     * selectObj只会返回第一个字段
     * selectCount/selectOne略
     */


    @Test
    public void selectLambda() {
        //创建方式 3种
        //    LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        LambdaQueryWrapper<User> userLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        userLambdaQueryWrapper.eq(User::getName, "tom").gt(User::getPassword, 0);
        userMapper.selectMaps(userLambdaQueryWrapper).stream().forEach(System.out::println);


        //多条件拼接
        System.out.println("-------------------");
        userLambdaQueryWrapper2.likeRight(User::getName, "to").and(lqw -> lqw.eq(User::getPassword, "123456"));
        userMapper.selectList(userLambdaQueryWrapper2).stream().forEach(System.out::println);
    }

    @Test
    public void lambda2() {
        new LambdaQueryChainWrapper<User>(userMapper).eq(User::getName, "tom").list().forEach(System.out::println);

    }




}
