package mybatistest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xxm.application.Application;
import xxm.dao.PersonMapper;
import xxm.pojo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void personMapperTest() {
        Person person = new Person();
        person.setName("test");
        person.setUsername("test001");
        person.setPassword("123456");
        personMapper.insert(person);

    }


}
