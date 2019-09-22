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
	private static int count;
	
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
        count++;
        System.out.println("count2=="+count);
        System.out.println("godos job2 execute end at! - " + new Date());
        if(count == 28){
            Scheduler scheeduler = context.getScheduler();
            JobKey jobKey = context.getTrigger().getJobKey();
            try {
    				scheeduler.pauseJob(jobKey);
    		   } catch (SchedulerException e) {
  	  			// TODO Auto-generated catch block
  	  			e.printStackTrace();
    		   }
          }

    }

}
