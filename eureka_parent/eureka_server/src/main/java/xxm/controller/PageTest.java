package xxm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageTest {

    @RequestMapping("page")
    public String page() {
        System.out.println("---------page----------");


        return "index";
    }


}
