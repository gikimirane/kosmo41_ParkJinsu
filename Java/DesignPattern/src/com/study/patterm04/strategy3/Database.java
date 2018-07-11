package com.study.patterm04.strategy3;

public abstract class Database {
	
	public String name;
	public double rows;
	
	//데이터베이스마다 접속 방식이 다르다.
	public abstract void connectDatabase();

}
