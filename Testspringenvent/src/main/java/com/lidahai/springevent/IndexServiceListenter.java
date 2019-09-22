package com.lidahai.springevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IndexServiceListenter implements ApplicationListener{
	IndexService indexService = new IndexService();
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof UserRigsterEvent){
			System.out.println("收到用户注册信息--"+event.getSource());
			indexService.indexuser();
		}
		
	}

}
