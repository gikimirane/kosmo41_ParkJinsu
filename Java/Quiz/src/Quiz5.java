
public class Quiz5 {

	public static void main(String[] args) {
		/*
		for(int i = 2; i < 10; i++)
		{
			if((i % 2) == 0)
			{
				System.out.println(i+"´Ü");
				for(int j = 1; j <= i; j++)
				{
					System.out.println(i+"X"+j+"="+(i*j));
				}
			}
		}
		*/
		/*
		int i = 2;
		
		while(i < 10)
		{
			if((i % 2) == 0)
			{
				System.out.println(i+"´Ü");
				int j = 1;
				while(j <= i)
				{
					System.out.println(i+"X"+j+"="+(i*j));
					j++;
				}
				i++;
			}
			i++;
		}
		*/
		int i = 2;
		do
		{
			if((i % 2) ==0)
			{
				int j = 1;
				System.out.println(i+"´Ü");
				do
				{
					System.out.println(i+"X"+j+"="+(i*j));
					j++;
				}while(j <= i);
				i++;
			}
			i++;
		}while(i < 10);
	}

}
