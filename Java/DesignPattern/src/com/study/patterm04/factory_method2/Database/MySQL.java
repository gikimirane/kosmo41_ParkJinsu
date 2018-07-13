package com.study.patterm04.factory_method2.Database;

//A 'ConcreteProduct' class
public class MySQL extends Database {
	
	public MySQL() {
		name = "MySQL";
		rows = 20;
	}

	@Override
	public void connectDatabase() {
		System.out.println(name + "에 접속했습니다");

	}

}
