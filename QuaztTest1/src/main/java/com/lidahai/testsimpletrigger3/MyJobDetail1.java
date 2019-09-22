package com.lidahai.testsimpletrigger3;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class MyJobDetail1 implements Job{
    public String name;
    
    
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if("xiaoming".equals(name)){
			System.out.println("time is =="+getTime()+"==hello myjobdetail -- 这是给小明的任务");
			System.out.println("发邮件给小明");
		}
		if("xiaogao".equals(name)){
			System.out.println("time is =="+getTime()+"==hello myjobdetail -- 这是给小高的任务");
			System.out.println("发邮件给小高");
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getTime(){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Long millis = System.currentTimeMillis();
		String returnString = dateformat.format( millis);
		return returnString;
	}
}
