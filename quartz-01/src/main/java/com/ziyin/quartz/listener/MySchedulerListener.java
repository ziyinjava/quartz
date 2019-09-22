package com.ziyin.quartz.listener;

import org.quartz.*;

/**
 * @author ziyin
 * @create 2019-09-22 17:52
 */
public class MySchedulerListener implements SchedulerListener {
	@Override
	public void jobScheduled(Trigger trigger) {
		String jobName = trigger.getJobKey().getName();
		System.out.println(jobName + " 完成部署");
	}

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		System.out.println(triggerKey + " 完成卸载");
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		System.out.println("触发器被移除 " + trigger.getJobKey().getName());
	}

	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		System.out.println(triggerKey + " 正在被暂停");
	}

	@Override
	public void triggersPaused(String triggerGroup) {
		System.out.println("触发器组 " + triggerGroup + " 正在被暂停");
	}

	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		System.out.println(triggerKey + " 正在从暂停中恢复");
	}

	@Override
	public void triggersResumed(String triggerGroup) {
		System.out.println("触发器组 " + triggerGroup + " 正在从暂停中恢复");
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		System.out.println(jobDetail.getKey() + " 添加工作任务");
	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		System.out.println(jobKey + " 删除工作任务");
	}

	@Override
	public void jobPaused(JobKey jobKey) {
		System.out.println(jobKey + " 工作任务正在被暂停");
	}

	@Override
	public void jobsPaused(String jobGroup) {
		System.out.println("工作任务组 " + jobGroup + " 正在被暂停");
	}

	@Override
	public void jobResumed(JobKey jobKey) {
		System.out.println(jobKey + " 正在从暂停中恢复");
	}

	@Override
	public void jobsResumed(String jobGroup) {
		System.out.println("工作任务组 " + jobGroup + " 正在从暂停中恢复");
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		System.out.println("产生严重错误时调用： " + msg + " " + cause.getUnderlyingException());
	}

	@Override
	public void schedulerInStandbyMode() {
		System.out.println("调度器在挂起模式下调用");
	}

	@Override
	public void schedulerStarted() {
		System.out.println("调度器 开启时调用");
	}

	@Override
	public void schedulerStarting() {
		System.out.println("调度器 正在开启时调用");
	}

	@Override
	public void schedulerShutdown() {
		System.out.println("调度器 已经被关闭 时调用");
	}

	@Override
	public void schedulerShuttingdown() {
		System.out.println("调度器 正在被关闭 时调用");
	}

	@Override
	public void schedulingDataCleared() {
		System.out.println("调度器的数据被清除时调用");
	}
}