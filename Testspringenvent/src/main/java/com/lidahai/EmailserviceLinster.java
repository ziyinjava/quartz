package com.lidahai;

public class EmailserviceLinster extends ParentLinster{
	EmailService emailService = new EmailService ();
	@Override
	public void excute() {
		emailService.Sendconfirmmail();
		
	}

}
