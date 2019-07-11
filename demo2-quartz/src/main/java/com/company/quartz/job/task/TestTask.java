package com.company.quartz.job.task;

import org.springframework.stereotype.Service;

@Service("testTask")
public class TestTask {

    //cron 0/30 * * * * ?  30秒执行一次
    public void test1(){
        System.out.println("执行定时任务test1");
    }

    //cron 0 0 12 * * ?  每天中午12点执行一次
    public void test2(){
        System.out.println("执行定时任务test2");
    }

    //cron 0 50 11 11 7 ? 2019 2019年7月11日11点50分执行一次
    public void test3(){
        System.out.println("执行定时任务test3");
    }

    public void test4(){
        System.out.println("执行定时任务test4");
    }
}
