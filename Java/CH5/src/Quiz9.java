
public class Quiz9 {

	public static void main(String[] args) {
		int sum = 0;
		int a = 1;
		do
		{
			if((a % 2) == 0)
			{
				sum = sum + a;
				a++;
			}
			else
			{
				a++;
			   continue;
			}
		}while(a <= 100);
		System.out.println("1 부터 100 까지의 짝수들의 합은 " + sum);

	}

}
