package com.example.demo.controller;

import com.example.demo.dao.AnimalDao;
import com.example.demo.pojo.Animal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Api(value = "Jpa增删改查", description = "jpa增删改查")
@RestController
public class JpaController {

    @Autowired
    private AnimalDao animalDao;

    @RequestMapping(value = "insert", method = RequestMethod.PUT)
    public void insert() {
        Animal animal = new Animal();
        animal.setAge(10);
        animal.setName("Cat.Tom");
        animalDao.save(animal);

    }

    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public List<Animal> selectAll() {

        List<Animal> all = animalDao.findAll();


        return all;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap update(Integer id) {
        HashMap<Object, Object> map = new HashMap<>();
        Animal animal = animalDao.getOne(id);
        map.put("before: ", animal);
        animal.setName("name" + new Date());
        animal.setAge(10);

        Animal save = animalDao.save(animal);
        map.put("after: ", save);
        return map;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(Integer id) {
        boolean b = animalDao.exists(id);
        if (b) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }


}
