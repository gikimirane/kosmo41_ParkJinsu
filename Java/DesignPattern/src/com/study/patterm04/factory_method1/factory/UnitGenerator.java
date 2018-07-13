package com.study.patterm04.factory_method1.factory;

import java.util.ArrayList;
import java.util.List;

import com.study.patterm04.factory_method1Unit.Unit;

///<summary>
/// The 'Creator' abstract class
///</summary>
public abstract class UnitGenerator {
	public List<Unit> units = new ArrayList<Unit>();
	
	public List<Unit> getUnits()
	{
		return units;
	}
	//Factory Method
	public abstract void creatUnits();

}
