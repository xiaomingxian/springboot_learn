package com.xxm.mbp.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxm.mbp.pojo.User;
import org.apache.ibatis.annotations.Param;
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
public interface UserMapper extends BaseMapper<User> {//AR模式

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectMy(@Param(Constants.WRAPPER) Wrapper<User> user);

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectPageMy(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> user);



}
