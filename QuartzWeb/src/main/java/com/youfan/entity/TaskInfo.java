package com.youfan.entity;

public class TaskInfo {
	  private String queryGroup ;//��ѯ������
	  private String queryJobName;//��ѯ��������
	  private String jobGroup;//������
	  private String jobName;//��������
	  private String jobClassName;//������·��
	  private String concurrentdegree;//������
      private String triggerType;//1�����������  2��cron���������
      private String rate;//ִ��Ƶ��
      private String times;//ִ�д���
      private String secondField;//��
      private String minutesField;//����
      private String hourField;//Сʱ
      private String dayField;//��
      private String monthField;//��
      private String weekField;//��
      
      private String jobKey;//����key

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
