package com.lidahai;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class Computer {
	
	private List<String> zujian = new ArrayList<String>();//所有的组件
	
	public void addzujian(String linjian){
		zujian.add(linjian);
	}
	
	public void show(){
		for(int i=0;i<zujian.size();i++){
			System.out.println("组件："+zujian.get(i));
		}
		System.out.println("电脑的所有零件");
	}

}
