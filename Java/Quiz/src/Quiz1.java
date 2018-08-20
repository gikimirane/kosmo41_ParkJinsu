import java.util.Scanner;

public class Quiz1 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		/*
		int sum = 0;
		
		for(int num = 1; num != 0;)
		{
			System.out.println("정수를 입력하시오");
			num = scan.nextInt();
			sum = sum + num;
		}
		System.out.println("입력한 정수들의 합은 " + sum);
		*/
		
		int sum = 0;
		int num = 1;
		while(num != 0)
		{
			System.out.println("정수를 입력하시오");
			num = scan.nextInt();
			sum = sum + num;
		}
		System.out.println("입력한 정수들의 합은 " + sum);
		
		/*
		int x = 0;
		int sum = 0;
		int num = 0;
		
		do
		{
			System.out.println("정수를 입력하세요");
			num = scan.nextInt();
			sum = sum + num;
			x++;
		}while(num != 0);
		System.out.println("sum : "+ sum);
		*/
	}
	

}
