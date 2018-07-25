package com.study.pattern04.factory_method2.Database;

//C	 'ConcreteProduct' class
public class Infomix extends Database {

	public Infomix() {
		name = "Infomix";
		rows = 20;
	}

	@Override
	public void connectDatabase() {
		System.out.println(name + "에 접속했습니다");

	}

}
