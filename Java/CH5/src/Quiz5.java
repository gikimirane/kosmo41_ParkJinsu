
public class Quiz5 {

	public static void main(String[] args) {
		
		for(int i = 2; i < 10; i++)
		{
			if(i % 2 == 0)
			{
				System.out.println(i + "´Ü");
			for(int j = 1; j <= i; j++)
			{
				
				System.out.println(i + " * " + j + " = " + (i*j));
			}
		}

	}
	
		/*for(int i = 2; i < 10; i++)
		{
			if(i % 2 == 1)
			{
				continue;
			}
			System.out.println(i + "´Ü");
			for(int j = 1; j < 10; j++)
			{
				System.out.println(i + " * " + j + " = " + (i*j));
				if(i == j)
				{
					break;
				}
			}
		}
		*/
  }
}
