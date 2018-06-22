
public class Quiz12 {

	public static void main(String[] args) {
		int sum = 0;
		for(int i = 1; i <=1000; i++)
		{
			sum = sum + i;
			System.out.print(i);
			if(i < 1000)
			{
				System.out.print("+");
			}
		}
		System.out.println(" = "+ sum);
		

	}

}
