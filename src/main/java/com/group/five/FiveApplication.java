package com.group.five;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.group.five.dao")
public class FiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(FiveApplication.class, args);
    }
}
