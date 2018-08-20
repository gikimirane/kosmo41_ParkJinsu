
public class Quiz5_12 {

	public static void main(String[] args) {
		int sum = 0;
		int i = 1;
		do
		{
			System.out.print(i);
			if(i < 1000)
			{
				System.out.print("+");
			}
			sum = sum + i;
			i++;
		}while(i <= 1000);
		System.out.print("="+sum);

	}

}
