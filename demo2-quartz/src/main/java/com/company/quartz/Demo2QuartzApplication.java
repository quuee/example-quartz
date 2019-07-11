package com.company.quartz;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 因为手动添加了分页插件配置，排除自动添加配置
 */
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class Demo2QuartzApplication {

    public static void main(String[] args) {

        SpringApplication.run(Demo2QuartzApplication.class,args);
    }
}
