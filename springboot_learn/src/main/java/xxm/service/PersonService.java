package xxm.service;

import xxm.pojo.Person;

public interface PersonService {


    void insert(Person person);

    Person queryById(String id);
}
