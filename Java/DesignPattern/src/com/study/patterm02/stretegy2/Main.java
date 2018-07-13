package com.study.patterm02.stretegy2;

public class Main {

	public static void main(String[] args) {
		GameCharacter character = new GameCharacter();
		character.fire();
		
		//전략적으로 무기를 선택한다.
		character.setWeapon(new Arrow());
		character.fire();
		
		character.setWeapon(new Bullet());
		character.fire();
		
		character.setWeapon(new Missile());
		character.fire();

	}

}
