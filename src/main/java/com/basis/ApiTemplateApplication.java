package com.basis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.basis.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ApiTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTemplateApplication.class, args);
    }

}
