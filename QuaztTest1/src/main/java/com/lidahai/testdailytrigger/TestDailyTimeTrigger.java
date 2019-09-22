package com.lidahai.testdailytrigger;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

public class TestDailyTimeTrigger {
	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory sched = new StdSchedulerFactory();
		Scheduler scheduler = sched.getScheduler();
		
		//我想这个任务每天9点开始执行，到下午的5点结束执行，每隔1小时执行一次
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("tr1", "group1").
				withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0))//这里是指9点开始
						.endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17, 0)).withIntervalInHours(1).withRepeatCount(14)).build();
		
//		//我想这个任务每天9点开始执行，到下午5点执行完毕，每个小时执行一次，周一到周四执行
//		DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0))//这里是指9点开始
//		.endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17, 0)).withIntervalInHours(1).withRepeatCount(14).onDaysOfTheWeek(java.util.Calendar.MONDAY,java.util.Calendar.TUESDAY,java.util.Calendar.WEDNESDAY,java.util.Calendar.TUESDAY);
		
		JobDetail jobdetail = JobBuilder.newJob(MyJobDetail.class).withIdentity("job1", "group1").usingJobData("user","boob").build();
		scheduler.scheduleJob(jobdetail, trigger);
		
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scheduler.shutdown();
		
	}

}
