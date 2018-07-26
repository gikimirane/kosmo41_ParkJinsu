package com.study.pattern04.factory_method2.factory;

import com.study.pattern04.factory_method2.Database.Database;

enum DBType
{
	MySQL,
	Oracle,
	Infomix
}
public abstract class DatabaseFactory {
	
	//Factory Method
	public abstract Database setDatabase();

}
