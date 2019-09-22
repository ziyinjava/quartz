package com.lidahai.springevent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

public class UserRigsterEvent extends ApplicationEvent{

	public UserRigsterEvent(String source) {
		super(source);
	}

}
