package xxm.dao;


import xxm.pojo.Person;

public interface PersonMapper {
    void insert(Person person);

    Person queryById(String id);
}
