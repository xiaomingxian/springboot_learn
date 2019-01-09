package com.example.demo.controller;

import com.example.demo.dao.AnimalDao;
import com.example.demo.pojo.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class JpaController {

    @Autowired
    private AnimalDao animalDao;

    @RequestMapping("insert")
    public void insert() {


        Animal animal = new Animal();
        animal.setAge(10);
        animal.setName("Cat.Tom");
        animalDao.save(animal);

    }

    @RequestMapping("selectAll")
    public List<Animal> selectAll() {

        List<Animal> all = animalDao.findAll();


        return all;
    }

    @RequestMapping("update")
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

    @RequestMapping("delete")
    public String delete(Integer id) {
        boolean b = animalDao.exists(id);
        if (b) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }


}
