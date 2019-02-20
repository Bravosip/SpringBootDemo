package com.example.demo.controller;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
public class HelloController {


    @RequestMapping("/sayHello")
    public String sayHello(){
        log.info("hello world 测试日志输出");
        return "Hello,world";
    }
}
