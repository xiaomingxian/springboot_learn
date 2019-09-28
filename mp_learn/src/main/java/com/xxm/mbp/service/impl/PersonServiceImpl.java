package com.xxm.mbp.service.impl;

import com.xxm.mbp.pojo.Person;
import com.xxm.mbp.dao.PersonMapper;
import com.xxm.mbp.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoming.xian
 * @since 2019-09-28
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
