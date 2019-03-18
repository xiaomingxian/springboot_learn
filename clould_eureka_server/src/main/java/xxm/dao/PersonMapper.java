package xxm.dao;


import xxm.pojo.Person;

import java.util.List;

public interface PersonMapper {
    void insert(Person person);

    Person queryById(String id);

    List<Person> select();
}
