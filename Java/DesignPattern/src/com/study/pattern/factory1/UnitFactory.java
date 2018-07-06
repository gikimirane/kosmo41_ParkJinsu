package com.study.pattern.factory1;

enum UnitType{
	Marine,
	Firebat,
	Medic
}
public class UnitFactory {
	public static Unit creatUnit(UnitType type) {
		Unit unit = null;
		//구체적인 생성 방법을 지정하여 생성한다.
		//생성시 파라미터가 있다면 생성자에 추가할 수도 있다.
		switch (type) {
		case Marine:
			unit = new Marine();
			break;
		case Firebat:
			unit = new Firebat();
			break;
		case Medic:
			unit = new Medic();
			break;

		}
		
		return unit;
	}
}
