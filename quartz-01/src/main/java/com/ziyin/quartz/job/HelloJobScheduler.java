package com.ziyin.quartz.job;

import org.quartz.*;

import java.time.LocalDateTime;

/**
 * @author ziyin
 * @create 2019-09-22 7:41
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HelloJobScheduler implements Job {


	/**
	 * @param jobExecutionContext quartz运行上下文环境, 可以获取jobDetail和Trigger
	 * @throws JobExecutionException
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("当前时间： " + LocalDateTime.now());
		System.err.println("任务开始的时间" + jobExecutionContext.getFireTime());
		System.err.println("下次任务开始的时间: " + jobExecutionContext.getNextFireTime());
	}
}
