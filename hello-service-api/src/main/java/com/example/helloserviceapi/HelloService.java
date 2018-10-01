package com.example.helloserviceapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("refactor")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping("/hello2")
    User hello(@RequestParam("name") String name,Integer age);
}
