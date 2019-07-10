package com.company.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.company.quartz.job.dao"})
public class Demo2QuartzApplication {

    public static void main(String[] args) {

        SpringApplication.run(Demo2QuartzApplication.class,args);
    }
}
