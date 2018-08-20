import java.util.Random;
public class Quiz15 {
	
	public static void main(String[] args) {
		for(;;)
		{
			Random randomV1 = new Random();
			int ran = randomV1.nextInt(10)+102;
			int n1 = ran/100;
			int n2 = (ran - (n1*100))/10;
			int n3 = ran-(n1*100) - (n2 *10);
		
			if((n1 != n2) && (n2 != n3) && (n1 != n3))
			{
				System.out.println(ran);
			}
		
		
		}
	}

}
