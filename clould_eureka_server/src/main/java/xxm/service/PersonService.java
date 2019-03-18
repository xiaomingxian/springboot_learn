package xxm.service;

import xxm.pojo.Person;

import java.util.List;

public interface PersonService {


    void insert(Person person);

    Person queryById(String id);

    List select();
}
