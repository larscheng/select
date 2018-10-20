//package com.slxy.www.config;
//import java.util.Date;
//
//import com.slxy.www.common.TimeHWUtil;
//import com.slxy.www.service.SelectProcessControlService;
//import com.slxy.www.service.SelectSubjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//import java.time.LocalDateTime;
//
//@Component
//@EnableScheduling
//public class TaskCronChange implements  SchedulingConfigurer{
//
//    @Autowired
//    private SelectProcessControlService selectProcessControlService;
//    @Autowired
//    private SelectSubjectService selectSubjectService;
//    public static String cron;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        //项目部署时，会在这里执行一次，从数据库拿到cron表达式
//        cron = "0/10 * * * * ?";
//
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                //自动结题
//                selectSubjectService.autoEnd();
//                System.out.println("I am going:" + LocalDateTime.now());
//            }
//        };
//        Trigger trigger = new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                //任务触发，可修改任务的执行周期.
//                //每一次任务触发，都会执行这里的方法一次，重新获取下一次的执行时间
//                cron = new TimeHWUtil().getCron(selectProcessControlService.selectById(6).getProEndTime()).substring(0,16);// 从数据库查询出来的
//                CronTrigger trigger = new CronTrigger(cron);
//                Date nextExec = trigger.nextExecutionTime(triggerContext);
//                return nextExec;
//            }
//        };
//        taskRegistrar.addTriggerTask(task, trigger);
//    }
//
//}
