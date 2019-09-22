package com.youfan.task;



import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob2 implements Job {
 
    public HelloJob2() {
    }

    
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
    	System.out.println("goods job2  execute...................................");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.getMessage();
        }
        System.out.println("godos job2 execute end at! - " + new Date());

    }

}
