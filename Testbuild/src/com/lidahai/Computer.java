package com.lidahai;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class Computer {
	
	private List<String> zujian = new ArrayList<String>();//���е����
	
	public void addzujian(String linjian){
		zujian.add(linjian);
	}
	
	public void show(){
		for(int i=0;i<zujian.size();i++){
			System.out.println("�����"+zujian.get(i));
		}
		System.out.println("���Ե��������");
	}

}
