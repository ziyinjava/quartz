package com.lidahai.springevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PointServiceLinster implements SmartApplicationListener{
	PointService pointService = new PointService();
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("收到用户注册信息--"+event.getSource());	
		pointService.addPoint();
		
	}

	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		
		return eventType ==  UserRigsterEvent.class;
	}

	public boolean supportsSourceType(Class<?> sourceType) {
		// TODO Auto-generated method stub
		return sourceType == String.class;
	}

}
