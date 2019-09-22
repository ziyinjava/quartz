package com.lidahai.springevent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{
    public static void main(String[] args)
    {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        applicationContext.publishEvent(new UserRigsterEvent("ะกร๗"));  
        applicationContext.registerShutdownHook();
    }
}
