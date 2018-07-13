package com.study.patterm02.strategy3;

public class Infomix extends Database {
	public Infomix() {
		name = "InforMix";
		rows = 40;
				
	}

	@Override
	public void connectDatabase() {
		System.out.println(name + "에 접속했습니다");

	}

}
