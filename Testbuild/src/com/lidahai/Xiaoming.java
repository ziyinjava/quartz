package com.lidahai;

public class Xiaoming {
	
	public static void main(String[] args) {
		LaoBan laoBan = new LaoBan();
		AbastrtBuilder abastrtBuilder = new ConcreateBuild();
		laoBan.mingling(abastrtBuilder);//����װ��Ա��������
		Computer computer = abastrtBuilder.buildComputter();//�������յĲ�Ʒ ������
		computer.show();
	}

}
