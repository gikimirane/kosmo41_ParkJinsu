class Calc3
{
	int add(int a, int b)
	{
		return a + b;
	}
	int add(int a)
	{
		return a + 1;
	}
	double add(double a, double b)
	{
		return a + b;
	}
}
public class Calculation2 {

	public static void main(String[] args) {
		Calc3 calc = new Calc3();
		int nRtn1 = calc.add(3, 9);
		int nRtn2 = calc.add(3);
		double nRtn3 = calc.add(3.0, 9.0);
		
		System.out.println("Rtn1 = " + nRtn1);
		System.out.println("Rtn2 = " + nRtn2);
		System.out.println("Rtn3 = " + nRtn3);

	}

}
