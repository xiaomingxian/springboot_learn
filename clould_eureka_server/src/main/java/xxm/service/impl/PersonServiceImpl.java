package xxm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xxm.dao.PersonMapper;
import xxm.pojo.Person;
import xxm.service.PersonService;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;


    @Override
    public void insert(Person person) {
        personMapper.insert(person);
    }

    @Override
    public Person queryById(String id) {
        return personMapper.queryById(id);
    }

    @Override
    public List select() {
        return personMapper.select
                ();
    }
}
