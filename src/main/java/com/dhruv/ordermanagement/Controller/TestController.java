package com.dhruv.ordermanagement.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {


    @RequestMapping("/hello")
    public String Testing(){
        System.out.println("It is inside Console.");
        return "Hello Order Management API";
    }


}
