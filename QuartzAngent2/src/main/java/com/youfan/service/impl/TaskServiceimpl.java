package com.youfan.service.impl;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.youfan.service.TaskService;

public class TaskServiceimpl implements TaskService{
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	public void resumeJob(String jobKey) throws SchedulerException {
		    Scheduler scheduler = schedulerFactory.getScheduler();
	        String[] keyArray=jobKey.split("\\."); 
	        scheduler.resumeJob(JobKey.jobKey(keyArray[1], keyArray[0]));
	        scheduler.start();
	}

}
