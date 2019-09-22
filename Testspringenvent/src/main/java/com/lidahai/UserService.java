package com.lidahai;

import java.util.ArrayList;
import java.util.List;

public class UserService {

	List<ParentLinster> list = new ArrayList<ParentLinster>();
	
	public void addListenter(ParentLinster parentLinster){
		list.add(parentLinster);
	}
	
	public void userregister(){
			System.out.println("ÓÃ»§×¢²á");
			for(ParentLinster parentLinster :list){
				parentLinster.excute();
			}
			
	}
	
	public static void main(String[] args) {
		UserService userService = new UserService();
		EmailserviceLinster emailserviceLinster = new EmailserviceLinster();
		IndexserviceLinster indexserviceLinster = new IndexserviceLinster();
		PointserviceLinster pointserviceLinster = new PointserviceLinster();
		PresentserviceLinster presentserviceLinster = new PresentserviceLinster();
		userService.addListenter(emailserviceLinster);
		userService.addListenter(indexserviceLinster);
		userService.addListenter(pointserviceLinster);
		userService.addListenter(presentserviceLinster);
		
		userService.userregister();
	}
}
