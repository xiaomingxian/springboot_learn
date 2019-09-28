package com.xxm.mbp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<User> wrapper = new QueryWrapper<>();
    }

}
