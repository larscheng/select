package com.slxy.www.config;

import com.slxy.www.common.TimeHWUtil;
import com.slxy.www.service.SelectProcessControlService;
import com.slxy.www.service.SelectSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/10/29 17:04
 */

@Configuration
@Component
public class AutoFinishSelect implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private SelectProcessControlService selectProcessControlService;
    @Autowired
    private SelectSubjectService selectSubjectService;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private ScheduledFuture<?> future;

    private static String cron = null;

    public String getCron(Date date){
        cron = new TimeHWUtil().getCron(date).substring(0, 16);
        return  cron;
    }

    public void update(Date date){
        if(future != null) {
            logger.info("---------------------------------清除线程池 ");
            future.cancel(true);
        }
        future = threadPoolTaskScheduler.schedule(task, new CronTrigger(getCron(date)));
        logger.info("---------------------------------更新自动结题业务 "+cron);
    }


    Runnable task = new Runnable() {
        @Override
        public void run() {
            selectSubjectService.autoEnd();
            logger.info("---------------------------------执行自动结题业务");
        }
    };

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Thread.sleep(1000);
        future = threadPoolTaskScheduler.schedule(task, new CronTrigger(getCron(selectProcessControlService.selectById(6).getProEndTime())));
        logger.info("---------------------------------初始化自动结题业务 "+cron);
    }
}
