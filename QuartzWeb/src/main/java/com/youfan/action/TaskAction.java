package com.youfan.action;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.StringMatcher.StringOperatorName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youfan.entity.TaskInfo;
import com.youfan.rpc.RPCClient;
import com.youfan.service.TaskService;
import com.youfan.util.PropertyRead;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("taskAction")
public class TaskAction {
	
	@Autowired
	SchedulerFactory schedulerFactory;
	
	Jedis jedis = new Jedis("192.168.253.142");
	
	private static List<TaskService> servicelist = new ArrayList<TaskService>();
	static{
		String hostlist = PropertyRead.getkey("hostlist");
		String[] hosts = hostlist.split(",");
		for(String host:hosts){
			TaskService service = RPCClient.getRemoteProxyObj(TaskService.class, new InetSocketAddress(host, 8088));
			servicelist.add(service);
		}
		
	}
	
	@RequestMapping("sayhello")
	public String sayhello(){
		return "sayhello";
	}
	
	@RequestMapping("list")
	public String tolist(HttpServletRequest request){
		 List<HashMap<String, Object>> jobList = new ArrayList<HashMap<String, Object>>();
		try {
			 Scheduler scheduler = schedulerFactory.getScheduler();
			 List<String> groups = scheduler.getJobGroupNames();
			 for(String group:groups){
		            Set<JobKey> jobKeys = scheduler.getJobKeys(new GroupMatcher(group, StringOperatorName.EQUALS){});
		            for(JobKey jobKey : jobKeys){
		                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		                HashMap<String, Object> jobInfoMap = new HashMap<String, Object>();
		                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		                String pinelines = jedis.get("concurrentdegree:"+jobKey.getName()+"."+jobKey.getGroup());
		                jobInfoMap.put("pinelines", pinelines);
		                jobInfoMap.put("triggers", triggers);
		                jobInfoMap.put("jobDetail", jobDetail);
		                jobList.add(jobInfoMap);
		            }
		        }
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("jobList", jobList);
		return "listJobs";
	}
	
	@RequestMapping("toAddJob")
	public String toAddJob(HttpServletRequest request){
		List<String> jobGroups = new ArrayList<String>();
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			jobGroups = scheduler.getJobGroupNames();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("jobGroups", jobGroups);
		return "addJob";
	}
	
	
	@RequestMapping("addJob")
	public String addJob(HttpServletRequest request,TaskInfo taskInfo){
	        String group = taskInfo.getJobGroup();
	        String jobName = taskInfo.getJobName();
	        String jobClassName = taskInfo.getJobClassName();
	        String concurrentdegree = taskInfo.getConcurrentdegree();
	        String triggerType = taskInfo.getTriggerType();
	        
	        Trigger trigger = null;
	        if("1".equals(triggerType)){
	            String rate = taskInfo.getRate();
	            String times = taskInfo.getTimes();
	            Integer rateInt = new Integer(rate);
	            Integer timesInt = new Integer(times);
	            trigger = newTrigger().
	            withIdentity(jobName, group).
	            withSchedule(simpleSchedule().
	            withIntervalInSeconds(rateInt).
	            withRepeatCount(timesInt)).build();
	        }
	        if("2".equals(triggerType)){
	            String second = taskInfo.getSecondField();
	            String minute = taskInfo.getMinutesField();
	            String hour = taskInfo.getHourField();
	            String day = taskInfo.getDayField();
	            String month = taskInfo.getMonthField();
	            String week = taskInfo.getWeekField();
	            String cronExpression = String.format("%s %s %s %s %s %s", second, minute, hour, day, month, week);
	            boolean isValid = CronExpression.isValidExpression(cronExpression);
	            if(isValid){
	            	 trigger = newTrigger()
	     	                .withIdentity(jobName, group)
	     	                .withSchedule(cronSchedule(cronExpression))
	     	                .build(); 
	            } 
	        }
	        try {
				 Class jobClass = Class.forName(jobClassName);
				 JobDetail job = newJob(jobClass).withIdentity(jobName, group).build();
				 jedis.set("concurrentdegree:"+jobName+"."+group, concurrentdegree);
			     Scheduler scheduler = schedulerFactory.getScheduler();
			     scheduler.scheduleJob(job, trigger);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "redirect:list";
	}
	
	
	@RequestMapping("resumeJob")
	private String resumeJob(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
        
        String jobKey = request.getParameter("jobKey").trim();
       
        for(TaskService service:servicelist){
        	service.resumeJob(jobKey);
        }
        
        return "redirect:list";
    }
	
	@RequestMapping("deleteJob")
	private String deleteJob(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
        String jobKey = request.getParameter("jobKey").trim();
        String[] keyArray=jobKey.split("\\.");
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.deleteJob(JobKey.jobKey(keyArray[1], keyArray[0]));
        scheduler.start();
        return "redirect:list";
    }
	
	@RequestMapping("toaddordertask")
	public String toaddordertask(HttpServletRequest request){
		 List<String> jobList = new ArrayList<String>();
		try {
			 Scheduler scheduler = schedulerFactory.getScheduler();
			 List<String> groups = scheduler.getJobGroupNames();
			 for(String group:groups){
		            Set<JobKey> jobKeys = scheduler.getJobKeys(new GroupMatcher(group, StringOperatorName.EQUALS){});
		            
		            for(JobKey jobKey : jobKeys){

		                jobList.add(jobKey.toString());
		            }
		        }
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("jobList", jobList);
		return "listjobkey";
	}
	
	
	@RequestMapping("addordertask")
	public String addordertask(HttpServletRequest request){
		String[] jobkey = request.getParameterValues("jobkey");
        String[] joborder = request.getParameterValues("joborder");
        String keystring = "";
        String valuestring = "";
        for(int i =0;i<joborder.length;i++){
        	//jobkey1==jobkey2==jobkey3==orderby,jobkey1*1==jobek2*2==jobkey3*3
        	String joborde = joborder[i];
        	if(joborde!=null&&!"".equals(joborde.trim())){
        		keystring += jobkey[i]+"==";
        		valuestring += jobkey[i]+"*"+joborder[i]+"==";
        	}
        }
        if(keystring!=null&&!"".equals(keystring.trim())){
        	keystring+= "orderby";
        	jedis.set(keystring.toString(), valuestring.toString());
        }
		return "redirect:listordertask";
	}
	
	@RequestMapping("listordertask")
	public String listordertask(HttpServletRequest request){
		Set<String> setkeys = jedis.keys("*==orderby*");
		Map<String,String> listmap = new HashMap<String,String>();
		for(String key :setkeys){
			listmap.put(key, jedis.get(key));
		}
		request.setAttribute("listmap", listmap);
        
		return "listorder";
	}
	
}
