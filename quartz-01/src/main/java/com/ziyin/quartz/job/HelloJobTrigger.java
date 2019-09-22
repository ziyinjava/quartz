package com.ziyin.quartz.job;

import org.quartz.*;

import java.time.LocalDateTime;

/**
 * @author ziyin
 * @create 2019-09-22 7:41
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HelloJobTrigger implements Job {


	/**
	 *
	 * @param jobExecutionContext quartz运行上下文环境, 可以获取jobDetail和Trigger
	 * @throws JobExecutionException
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LocalDateTime localDateTime = LocalDateTime.now();

		// 获取JobDetail的数据
		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		System.out.println(jobDetail.getKey().getName());
		System.out.println(jobDetail.getJobDataMap().get("message"));

		// 获取Trigger的数据
		Trigger trigger = jobExecutionContext.getTrigger();
		System.out.println(trigger.getKey().getName());

		// 获取其他属性
		System.err.println("当前任务的时间" + jobExecutionContext.getFireTime());
		System.err.println("下次任务的时间: " + jobExecutionContext.getNextFireTime());

		System.out.println("任务开始时间：" + trigger.getStartTime());
		System.out.println("任务结束时间：" + trigger.getEndTime());

	}
}
