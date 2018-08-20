
public class Quiz3 {

	public static void main(String[] args) {
		/*
		int i = 0;
		System.out.println("7의 배수와 9의 배수");
		while(i < 100)
		{
			if(((i % 7) == 0) || ((i % 9) == 0))
			{
				System.out.println(i);
			}
			i++;
		}
		*/
		/*
		int i = 1;
		System.out.println("7의 배수와 9의 배수");
		do
		{
			if(((i % 7) == 0) || ((i % 9) == 0))
			{
				System.out.println(i);
			}
			i++;
		}while(i < 100);
		*/
		System.out.println("7의 배수와 9의 배수");
		for(int i = 1; i < 100; i++)
		{
			if(((i % 7) == 0) || ((i % 9) == 0))
			{
				System.out.println(i);
			}
		}

	}

}
