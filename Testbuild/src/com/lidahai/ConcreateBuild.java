package com.lidahai;

public class ConcreateBuild extends AbastrtBuilder{
	Computer computter = new Computer();

	@Override
	public void makemouse() {
		computter.addzujian("���");
		
	}

	@Override
	public void makekeyboard() {
		computter.addzujian("����");
		
	}

	@Override
	public void makejixiang() {
		computter.addzujian("����");
		
	}

	@Override
	public void makezhuban() {
		computter.addzujian("����");
		
	}

	@Override
	public Computer buildComputter() {
		return computter;
	}

}
