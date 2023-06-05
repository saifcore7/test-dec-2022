package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    public String hello(){
        return "<h2> Hello </h2>" +
                "\n <h1> I created this demo spring boot app for <a href=\"https://www.redcarpetup.com\" target=\"_blank\"><span style=\"color: red;\">RedCarpetUp</a></span></h1>" ;
    }
}
