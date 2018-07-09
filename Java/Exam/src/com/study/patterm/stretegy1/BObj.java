package com.study.patterm.stretegy1;


public class BObj {
	AInterface ainter;
	
	public BObj() {
		ainter = new AImplement();
	}
	
	public void SomeFunc() {
		
		//다음 기능이 필요합니다. A님 만들어 주세요
		//기능 구현을 위임 - Delegate
		System.out.println("AAA");
        System.out.println("AAA");
		System.out.println("AAA");
		
		//Delegate 사용
		ainter.funcA();
		ainter.funcA();
		ainter.funcA();

	}

}
