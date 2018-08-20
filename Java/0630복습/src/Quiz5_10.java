import java.util.Scanner;

public class Quiz5_10 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("두개의 정수를 입력하세요");
		int num1 = s.nextInt();
		int num2 = s.nextInt();
		int sum = 0;
		
		if(num1 > num2)
		{
			for(;num1 >= num2; num1--)
			{
				System.out.print(num1);
				if(num1 > num2)
				{
					System.out.print(" + ");
				}
				sum = sum + num1;
			}
			System.out.print(" = " + sum);
		}
		else
		{
			for(;num1 <= num2; num1++)
			{
				System.out.print(num1);
				if(num1 < num2)
				{
					System.out.print(" + ");
				}
				sum = sum + num1;
			}
			System.out.print(" = "+sum);
		}

	}

}
