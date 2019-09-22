package com.lidahai;

/**
 * 构建电脑的抽象类
 * @author Administrator
 *
 */
public abstract class AbastrtBuilder {
	//构建鼠标
	public abstract void makemouse();
	//构建键盘
	public abstract void makekeyboard();
	//构建机箱
	public abstract void makejixiang();
	//构建主板
	public abstract void makezhuban();
	//返回构建好的电脑
	public abstract Computer buildComputter();
}
