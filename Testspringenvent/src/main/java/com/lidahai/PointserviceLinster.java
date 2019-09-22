package com.lidahai;

public class PointserviceLinster extends ParentLinster{
	PointService pointService = new PointService ();
	@Override
	public void excute() {
		pointService.addPoint();
		
	}

}
