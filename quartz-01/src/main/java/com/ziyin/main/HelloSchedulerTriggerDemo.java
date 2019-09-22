package com.ziyin.main;

import com.ziyin.quartz.job.HelloJobCronTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author ziyin
 * @create 2019-09-22 7:44
 */
public class HelloSchedulerTriggerDemo {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		// 调度器(scheduler),工厂模式获取调度器实例
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// 任务实例(jobDetail), 加载任务类,与我们的任务类绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJobCronTrigger.class)
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
				// 3/30 从第几秒开始，没30秒执行一次， 3s, 33s, 0/30--> 0s, 30s，  3 * * * * ? 每第3秒执行
				.withSchedule(CronScheduleBuilder.cronSchedule("3/30 * * * * ?"))
				.build();

		// 让调度器关联任务和触发器, 保证按照触发器定义的条件执行任务
		Date date = scheduler.scheduleJob(jobDetail, trigger);

		System.err.println("<<第一次任务开始时间>>: " + date);
		// 启动
		scheduler.start();

		//挂起
		scheduler.standby();

		Thread.sleep(date.getTime() - System.currentTimeMillis() + 1000);

		scheduler.start();


//		scheduler.shutdown();
	}
}
