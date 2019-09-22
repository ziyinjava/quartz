package com.lidahai.testcalendarTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestcalendarTrigger {
	
	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory schefa = new StdSchedulerFactory();
		Scheduler sche = schefa.getScheduler();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("tr1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?")).build();
		JobDetail jobDetail = JobBuilder.newJob(MyJobDetail.class).withIdentity("job1", "group1").usingJobData("user", "xiaogao").build();
		sche.scheduleJob(jobDetail, trigger);
		sche.start();
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sche.shutdown();
	}

}
