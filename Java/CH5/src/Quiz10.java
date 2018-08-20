import java.util.Scanner;
public class Quiz10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int sum = 0;
		
		System.out.println("두개의 정수를 입력하시오");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		if(num1 > num2)
		{
			for(; num1 >= num2; num1--)
			{
				sum = sum + num1;
				System.out.print(num1);
				if(num1 > num2)
				{
					System.out.print("+");
				}
			}
			System.out.println("=" + sum);
		}
		else
		{
			for(; num2 >= num1; num2--)
			{
				sum = sum +num2;
				System.out.print(num2);
				if(num2 > num1)
				{
					System.out.print("+");
				}
			}
			System.out.println("=" + sum);
		}

	}

}
