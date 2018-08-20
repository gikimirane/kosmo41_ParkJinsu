import java.util.Scanner;

public class Quiz10 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int num1 = s.nextInt();
		System.out.println("숫자를 입력하세요");
		int num2 = s.nextInt();
		int sum = 0;
		if(num1 > num2)
		{
			for(;num1 >= num2; num1--)
			{
				System.out.print(num1);
				if(num1 > num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
			}
		}
		else if(num1 < num2)
		{
			for(;num1 <= num2; num1++)
			{
				System.out.print(num1);
				if(num1 < num2)
				{
					System.out.print("+");
				}
				sum = sum + num1;
			}
		}
		System.out.println("="+sum);

	}

}
