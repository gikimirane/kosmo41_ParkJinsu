package com.study.pattern04.factory_method1.factory;

import com.study.pattern04.factory_method1Unit.Firebat;
import com.study.pattern04.factory_method1Unit.Marine;

///B'ConcreteCreator' class

public class PatternBGenerator extends UnitGenerator {

	@Override
	public void creatUnits() {
		units.add(new Firebat());
		units.add(new Firebat());
		units.add(new Firebat());
		units.add(new Firebat());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());

	}

}
