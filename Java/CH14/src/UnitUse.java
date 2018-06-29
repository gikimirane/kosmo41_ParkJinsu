abstract class Unit 
{
	abstract void attack();
}

class Marine extends Unit
{
	public void attack()
	{
		System.out.println("총으로 공격");
		System.out.println("공격력 10으로 공격");
	}
}

class Zealot extends Unit
{
	public void attack()
	{
		System.out.println("손으로 공격");
		System.out.println("공격력 8으로 공격");
	}
}

class Zergling extends Unit
{
	public void attack()
	{
		System.out.println("입으로 공격");
		System.out.println("공격력 9으로 공격");
	}
}
public class UnitUse {

	public static void main(String[] args) {
		
		Unit unit1 = new Marine();
		unit1.attack();
		
		Unit unit2 = new Zealot();
		unit2.attack();
		
		Unit unit3 = new Zergling();
		unit3.attack();
	}

}
