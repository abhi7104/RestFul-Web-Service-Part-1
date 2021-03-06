package com.Application.webService.helloworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/hello-world")
    public String printHelloWorld(){
        return "Hello world";
    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean getHelloWorld(){
        return new HelloWorldBean("Hello World Bean");
    }


}
