
public class Quiz9 {

	public static void main(String[] args) {
		int sum = 0;
		/*
		int i = 0;
		do
		{	
			if((i % 2) == 0)
			{
				sum = sum + i;
				i++;
			}
			i++;
		}while(i <= 100);
		System.out.println("1부터 100까지의 짝수들의 합은 "+sum);
		*/
		/*int i = 0;
		while(i <= 100)
		{
			if((i % 2) == 0)
			{
				sum = sum + i;
				i++;
			}
			i++;
		}
		System.out.println("1부터 100까지의 짝수들의 합은 "+sum);
		*/
		for(int i = 0; i <= 100; i++)
		{
			if((i % 2) ==0)
			{
				sum = sum + i;
			}
		}
		System.out.println("1부터 100까지의 짝수들의 합은 "+sum);
	}

}
