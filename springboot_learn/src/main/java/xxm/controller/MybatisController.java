 package xxm.controller;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;
 import xxm.pojo.Person;
 import xxm.service.PersonService;

 @Controller
 public class MybatisController {


     @Autowired
     private PersonService personService;


     @RequestMapping("insert")
     @ResponseBody
     public void insert(@ModelAttribute Person person) {
         personService.insert(person);

     }

 }
