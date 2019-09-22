package com.ziyin.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * @author ziyin
 * @create 2019-09-22 17:34
 */
public class MyTriggerListener implements TriggerListener {

	private String name;

	public MyTriggerListener(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * 当与监听器相关联的Trigger被触发，Job上的execute()方法将被执行时，Scheduler就调用该
	 * 方法。
	 * @param trigger
	 * @param context
	 */
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		String triggerName = trigger.getKey().getName();
		System.out.println(triggerName + "触发");
	}

	/**
	 * 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。TriggerListener 给
	 * 了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行
	 * @param trigger
	 * @param context
	 * @return
	 */
	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		String triggerName = trigger.getKey().getName();
		System.out.println(triggerName + "没有触发");
		return true;
	}

	/**
	 * Scheduler 调用这个方法是在 Trigger 错过触发时。你应该关注此方法中持续时间长的逻
	 * 辑：在出现许多错过触发的 Trigger 时，长逻辑会导致骨牌效应。你应当保持这上方法尽量的小。
	 * @param trigger
	 */
	@Override
	public void triggerMisfired(Trigger trigger) {
		String triggerName = trigger.getKey().getName();
		System.out.println(triggerName + "错过触发");
	}

	/**
	 * Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法。
	 * @param trigger
	 * @param context
	 * @param triggerInstructionCode
	 */
	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
		String triggerName = trigger.getKey().getName();
		System.out.println(triggerName + "完成之后触发");
	}
}
