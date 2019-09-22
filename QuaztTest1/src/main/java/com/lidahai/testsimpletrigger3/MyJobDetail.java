package com.lidahai.testsimpletrigger3;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJobDetail implements Job{
    private String name;
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		Map<String, Object> map = jobDetail.getJobDataMap();
		String name = (String) map.get("user");
		if("xiaoming".equals(name)){
			System.out.println("time is =="+getTime()+"==hello myjobdetail -- 这是给小明的任务");
			System.out.println("发邮件给小明");
		}
		if("xiaogao".equals(name)){
			System.out.println("time is =="+getTime()+"==hello myjobdetail -- 这是给小高的任务");
			System.out.println("发邮件给小高");
		}
		
	}
	
	public String getName(String name){
		return this.name;
	}
	
	public String getTime(){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Long millis = System.currentTimeMillis();
		String returnString = dateformat.format( millis);
		return returnString;
	}
}
