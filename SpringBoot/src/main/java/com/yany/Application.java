package com.yany;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanyong on 2017/1/24.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.yany")
@MapperScan(value="com.yany.mapper")
@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
