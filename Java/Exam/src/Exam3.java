import java.util.Random;
class MyRandom
{
	void Rand1()
	{		
		for(;;)
		{
			Random randomV1 = new Random();
			int ran = randomV1.nextInt(899)+100;
			int n1 = ran/100;
			int n2 = (ran - (n1*100))/10;
			int n3 = ran-(n1*100) - (n2 *10);
	
			if((n1 != n2) && (n2 != n3) && (n1 != n3))
			{
				System.out.println(ran);
				break;
			}
	
	
		}
	}
}

public class Exam3 {
	
	public static void main(String[] args) {
		MyRandom aaa = new MyRandom();
		aaa.Rand1();
	
	}

}
