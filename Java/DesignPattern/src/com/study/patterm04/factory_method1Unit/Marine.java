package com.study.patterm04.factory_method1Unit;
///<summary>
///Marine 'concreteProduct' class
///</summary>
public class Marine extends Unit {
	
	public Marine() {
		type = UnitType.Marine;
		name = "Marine";
		hp = 100;
		exp = 50;
		
		System.out.println(this.name + " 생성 !!!");
	}

	@Override
	public void attack() {
		System.out.println(this.name + " 공격 !!!");

	}

}
