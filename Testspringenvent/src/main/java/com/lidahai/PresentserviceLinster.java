package com.lidahai;

public class PresentserviceLinster extends ParentLinster{
	PresentService presentservice = new PresentService();
	@Override
	public void excute() {
		presentservice.presentdalibao();
	}

}
