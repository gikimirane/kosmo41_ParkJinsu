package com.study.patterm04.factory_method1.factory;

import com.study.patterm04.factory_method1Unit.Marine;

///A 'ConcreteCreator' class
public class PatternAGenerator extends UnitGenerator {
	
	@Override
	public void creatUnits()
	{
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		
	}

}
