enum unitType
{
	Marine,
	Firebat,
	Medic
	
}
public class UnitFactory  {
	
	public static Unit creatUnit(unitType type)
	{
		Unit unit = null;
		
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
