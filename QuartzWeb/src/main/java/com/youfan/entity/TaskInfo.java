package com.youfan.entity;

public class TaskInfo {
	  private String queryGroup ;//查询组名称
	  private String queryJobName;//查询任务名称
	  private String jobGroup;//组名称
	  private String jobName;//任务名称
	  private String jobClassName;//任务类路径
	  private String concurrentdegree;//并发度
      private String triggerType;//1、简单任务调度  2、cron任务调度器
      private String rate;//执行频率
      private String times;//执行次数
      private String secondField;//秒
      private String minutesField;//分钟
      private String hourField;//小时
      private String dayField;//天
      private String monthField;//月
      private String weekField;//周
      
      private String jobKey;//任务key

	public String getQueryGroup() {
		return queryGroup;
	}

	public void setQueryGroup(String queryGroup) {
		this.queryGroup = queryGroup;
	}

	public String getQueryJobName() {
		return queryJobName;
	}

	public void setQueryJobName(String queryJobName) {
		this.queryJobName = queryJobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getConcurrentdegree() {
		return concurrentdegree;
	}

	public void setConcurrentdegree(String concurrentdegree) {
		this.concurrentdegree = concurrentdegree;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getSecondField() {
		return secondField;
	}

	public void setSecondField(String secondField) {
		this.secondField = secondField;
	}

	public String getMinutesField() {
		return minutesField;
	}

	public void setMinutesField(String minutesField) {
		this.minutesField = minutesField;
	}

	public String getHourField() {
		return hourField;
	}

	public void setHourField(String hourField) {
		this.hourField = hourField;
	}

	public String getDayField() {
		return dayField;
	}

	public void setDayField(String dayField) {
		this.dayField = dayField;
	}

	public String getMonthField() {
		return monthField;
	}

	public void setMonthField(String monthField) {
		this.monthField = monthField;
	}

	public String getWeekField() {
		return weekField;
	}

	public void setWeekField(String weekField) {
		this.weekField = weekField;
	}

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
      
	
      
      
}
