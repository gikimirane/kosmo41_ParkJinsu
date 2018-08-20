import java.util.Random;

class MyRandom1
{
	void RandPrint()
	{
		
	}
}
public class Test2 {

	public static void main(String[] args) {
		
		Random ran = new Random();
		
		for(;;)
		{
			int num1 = ran.nextInt(10);
			int num2 = ran.nextInt(10);
			int num3 = ran.nextInt(10);
			if((num1 != num2) && (num2 != num3) && (num1 != num3))
			{
				System.out.print(num1);
				System.out.print(num2);
				System.out.print(num3);
				break;
			}
		}

	}

}
