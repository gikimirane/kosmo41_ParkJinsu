import java.util.Scanner;
public class Quiz10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int sum = 0;
		System.out.println("첫번째 정수를 입력하세요");
		int num1 = scan.nextInt();
		System.out.println("두번째 정수를 입력하세요");
		int num2 = scan.nextInt();
		/*if(num1 > num2)
		{
			for(; num1 >= num2; num1--)
			{
				System.out.print(num1);
				if(num1 > num2)
				{
					System.out.print("+");
				}
				sum = sum +num1;
			}
			System.out.println("="+sum);
		}
		else
		{
			for(; num1 <= num2; num1++)
			{
				System.out.print(num1);
				if(num1 < num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
			}
			System.out.println("="+sum);
		}*/
		/*if(num1 > num2)
		{
			while(num1 >= num2)
			{
				System.out.print(num1);
				if(num1 > num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
				num1--;
			}
			System.out.println("="+sum);
		}
		else
		{
			while(num1 <= num2)
			{
				System.out.print(num1);
				if(num1 < num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
				num1++;
			}
			System.out.println("="+sum);
		}*/
		if(num1 > num2)
		{
			do
			{
				System.out.print(num1);
				if(num1 > num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
				num1--;
			}while(num1 >= num2);
			System.out.println("="+sum);
		}
		else
		{
			do
			{
				System.out.print(num1);
				if(num1 < num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
				num1++;
			}while(num1 <= num2);
			System.out.println("="+sum);
		}

	}

}
