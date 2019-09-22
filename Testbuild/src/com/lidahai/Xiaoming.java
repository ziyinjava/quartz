package com.lidahai;

public class Xiaoming {
	
	public static void main(String[] args) {
		LaoBan laoBan = new LaoBan();
		AbastrtBuilder abastrtBuilder = new ConcreateBuild();
		laoBan.mingling(abastrtBuilder);//命令装机员构建电脑
		Computer computer = abastrtBuilder.buildComputter();//产生最终的产品 ，电脑
		computer.show();
	}

}
