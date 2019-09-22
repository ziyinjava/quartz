package com.lidahai;

public class IndexserviceLinster extends ParentLinster{
	IndexService indexService = new IndexService ();
	@Override
	public void excute() {
		indexService.indexuser();
		
	}

}
