package com.ziyin.main;

import com.ziyin.quartz.job.HelloJobTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author ziyin
 * @create 2019-09-22 7:44
 */
public class HelloSchedulerCronDemo {
	public static void main(String[] args) throws SchedulerException {
		// 调度器(scheduler),工厂模式获取调度器实例
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// 任务实例(jobDetail), 加载任务类,与我们的任务类绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJobTrigger.class)
				// param1:任务的名称,唯一实例; param2:任务组的名称
				.withIdentity("job1", "group1")
				.usingJobData("message","jobDetailMessage")
				.build();

		System.out.println("名称: " + jobDetail.getKey().getName());
		// 不指定组名默认DEFAULT
		System.out.println("组名: " + jobDetail.getKey().getGroup());
		System.out.println("任务类:" + jobDetail.getJobClass().getName());


		// 触发器(Trigger)
		Trigger trigger = TriggerBuilder.newTrigger()
				// param1:触发器的名称,param2:触发器组的名称
				.withIdentity("trigger1", "group1")
				.usingJobData("message","triggerDetailMessage")
				.usingJobData("count",1)
				.startAt(new Date())
				.endAt(new Date(System.currentTimeMillis() + 1000 * 60))
				//.startNow()
				// 每5秒重复执行一次. repeatCount默认从0开始，设置3也就是一共执行4次
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5).withRepeatCount(3))
				.build();

		// 让调度器关联任务和触发器, 保证按照触发器定义的条件执行任务
		scheduler.scheduleJob(jobDetail,trigger);
		// 启动
		scheduler.start();

//		scheduler.shutdown();
	}
}
