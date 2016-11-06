package net.therap.mealplanner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bashir
 * @since 11/6/16
 */

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String helloWorld(){
        return "hello";
    }
}
