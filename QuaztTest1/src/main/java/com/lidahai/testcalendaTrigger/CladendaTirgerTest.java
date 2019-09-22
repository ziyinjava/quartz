package com.lidahai.testcalendaTrigger;

import java.util.Date;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.lidahai.testsimpletrigger.MyJobDetail;

public class CladendaTirgerTest {
	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory shed = new StdSchedulerFactory();
		Scheduler scheduler = shed.getScheduler();
		//我要在5秒之后启动任务，每隔2秒执行任务，最多执行501
		long currentTime = System.currentTimeMillis();
		long delayTime = currentTime + 5*1000l;
		
		//每月执行一次
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("tr1", "group1").
				startAt(new Date(delayTime)).withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(1)).build();
//		CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInWeeks(1)
		
		JobDetail jobDetail = JobBuilder.newJob(MyJobDetail.class).withIdentity("job1", "group1").usingJobData("user", "lidahai").build();
		
		scheduler.scheduleJob(jobDetail, trigger);
		 
		scheduler.start();
		
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduler.shutdown();
	}

}
