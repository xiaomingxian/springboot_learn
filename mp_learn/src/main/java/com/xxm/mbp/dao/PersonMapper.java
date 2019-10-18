package com.xxm.mbp.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxm.mbp.pojo.Person;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoming.xian
 * @since 2019-09-28
 */
public interface PersonMapper extends BaseMapper<Person> {


    @SqlParser(filter = true)//不查租户信息
    @Select("select * from person")
    List<Person> selectFilter();


    @SqlParser(filter = true)
    Integer selectCountTotal();

}
