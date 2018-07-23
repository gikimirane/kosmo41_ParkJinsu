import java.util.Random;

public class Quiz15 {

	public static void main(String[] args) {
		Random ran = new Random();
		for(;;)
		{
			int num1 = ran.nextInt(9)+1;
			int num2 = ran.nextInt(10);
			int num3 = ran.nextInt(10);
			
			if((num1 != num2) && (num1 != num3) && (num2 != num3))
			{
				System.out.println((num1*100)+(num2*10)+num3);
				break;
			}
		}
			
	}

}
