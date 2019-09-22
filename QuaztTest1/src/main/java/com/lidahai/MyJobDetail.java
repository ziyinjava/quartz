package com.lidahai;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJobDetail implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		Map map = jobDetail.getJobDataMap();
		System.out.println("time is =="+getTime()+"==hello myjobdetail -- data is "+map.get("user"));
	}

	public String getTime(){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Long millis = System.currentTimeMillis();
		String returnString = dateformat.format( millis);
		return returnString;
	}
}
