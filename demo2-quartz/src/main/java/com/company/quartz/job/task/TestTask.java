package com.company.quartz.job.task;

import org.springframework.stereotype.Service;

@Service("testTask")
public class TestTask {

    public void test1(){
        System.out.println("执行定时任务test1");
    }
}
