package com.study.pattern.factory1;
/* Simple Factory Pattern
 * 
 */
public class Main {

	public static void main(String[] args) {
		Unit unit1 = UnitFactory.creatUnit(UnitType.Marine);
		Unit unit2 = UnitFactory.creatUnit(UnitType.Firebat);
		Unit unit3 = UnitFactory.creatUnit(UnitType.Medic);
		
		unit1.move();
		unit2.move();
		unit3.move();

	}

}
