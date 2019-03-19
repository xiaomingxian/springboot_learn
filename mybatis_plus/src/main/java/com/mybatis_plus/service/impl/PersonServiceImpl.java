package com.mybatis_plus.service.impl;

import com.mybatis_plus.pojo.Person;
import com.mybatis_plus.dao.PersonMapper;
import com.mybatis_plus.service.PersonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxm123
 * @since 2019-03-19
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
