
public class Main {

	public static void main(String[] args) {
		Unit unit1 = UnitFactory.creatUnit(unitType.Marine);
		Unit unit2 = UnitFactory.creatUnit(unitType.Firebat);
		Unit unit3 = UnitFactory.creatUnit(unitType.Medic);
		
		unit1.move();
		unit2.move();
		unit3.move();

	}

}
