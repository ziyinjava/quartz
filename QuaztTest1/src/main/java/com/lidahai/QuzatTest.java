package com.lidahai;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CalendarIntervalTrigger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.JobBuilder.newJob;

public class QuzatTest {
	public static void main(String[] args) throws SchedulerException {
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		Trigger trigger = newTrigger().withIdentity("tr1", "group1")//设置触发器的名字和组别
				.startNow()//启动trigger
				.withSchedule(simpleSchedule().withIntervalInSeconds(1)//1秒触发一次任务
						.repeatForever()//不间断去触发
						).build();
		JobDetail jobdetail = newJob(MyJobDetail.class).withIdentity("jobdetail1", "group1").
				usingJobData("user", "bob")//添加实例共享数据
				.build();
		scheduler.scheduleJob(jobdetail, trigger);
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
