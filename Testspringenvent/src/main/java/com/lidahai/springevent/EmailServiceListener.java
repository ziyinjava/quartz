package com.lidahai.springevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceListener implements ApplicationListener{
	EmailService emailService = new EmailService();
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof UserRigsterEvent){
			System.out.println("�յ��û�ע����Ϣ--"+event.getSource());
			emailService.Sendconfirmmail();
		}
		
	}

}
