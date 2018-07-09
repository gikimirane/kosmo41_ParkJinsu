package com.study.patterm.stretegy2;

public class Main {

	public static void main(String[] args) {
		GameCharacter character = new GameCharacter();
		character.fire();
		
		character.setWeapon(new Arrow());
		character.fire();
		
		character.setWeapon(new Bullet());
		character.fire();
		
		character.setWeapon(new Missile());
		character.fire();

	}

}
