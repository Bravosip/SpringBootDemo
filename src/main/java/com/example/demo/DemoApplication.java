package com.example.demo;

import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Log4j
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        log.info("SpringBootDemo开始启动...........");
        SpringApplication.run(DemoApplication.class, args);
    }
}
