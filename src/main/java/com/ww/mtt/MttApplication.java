package com.ww.mtt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.ww.mtt.dao"})
public class MttApplication {

    public static void main(String[] args) {
        SpringApplication.run(MttApplication.class, args);
    }

}
