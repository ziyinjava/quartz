package com.youfan.service;

import org.quartz.SchedulerException;

public interface TaskService {
	void resumeJob(String jobKey) throws SchedulerException;
}
