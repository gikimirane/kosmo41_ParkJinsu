
public class Quiz5_9 {

	public static void main(String[] args) {
		int sum = 0;
		int i = 1;
		do
		{
			if(i % 2 == 0)
			{
				sum = sum + i;
			}
			i++;
		}while(i < 101);
		System.out.println("모든 짝수들의 합은 : " + sum);
	}

}
