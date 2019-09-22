package com.lidahai;

public class ConcreateBuild extends AbastrtBuilder{
	Computer computter = new Computer();

	@Override
	public void makemouse() {
		computter.addzujian("Êó±ê");
		
	}

	@Override
	public void makekeyboard() {
		computter.addzujian("¼üÅÌ");
		
	}

	@Override
	public void makejixiang() {
		computter.addzujian("»úÏä");
		
	}

	@Override
	public void makezhuban() {
		computter.addzujian("Ö÷°å");
		
	}

	@Override
	public Computer buildComputter() {
		return computter;
	}

}
