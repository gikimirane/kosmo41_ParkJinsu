import java.util.Random;
public class Quiz15 {

	public static void main(String[] args) {
		
		
		for(;;)
		{
			Random ran = new Random();
			
			int random = ran.nextInt(898)+102;
			int n1 = random/100;
			int n2 = (random-(n1*100))/10;
			int n3 = random -(n1*100)-(n2*10);
			if((n1 != n2) && (n2 != n3) && (n1 != n3))
			{
				System.out.println(random);
				break;
			}
			
		}	
	}

}
