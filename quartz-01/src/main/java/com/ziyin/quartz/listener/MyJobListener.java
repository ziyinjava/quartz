package com.ziyin.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author ziyin
 * @create 2019-09-22 12:47
 */
public class MyJobListener implements JobListener {
	@Override
	public String getName() {
		String name = getClass().getSimpleName();
		System.err.println("监听器的名称是："+name);
		return name;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().getName();
		System.err.println("jobToBeExecuted："+jobName+" Scheduler在JobDetail将要被执行时调用这个方法");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().getName();
		System.err.println("jobExecutionVetoed："+jobName+" Scheduler在JobDetail即将被执行，但又被TriggerListerner否决时会调用该方法");	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().getName();
		System.err.println("jobWasExecuted："+jobName+" Scheduler在JobDetail被执行之后调用这个方法");
	}
}
