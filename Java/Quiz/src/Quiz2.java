import java.util.Scanner;
public class Quiz2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		int sum = 0;
		int num1 = 0;
		
		System.out.println("몇 개의 정수를 입력하시겠습니까?");
		int count = scan.nextInt();
		
		for(int num = 1; num <= count; num++)
		{
			System.out.println("정수를 입력하세요");
			num1 = scan.nextInt();
			sum = sum + num1;
		}
		System.out.println("입력하신 값들의 평균 값은 " + ((double)sum / (double)count));
		*/
		/*
		int sum = 0;
		int num = 1;
		int num2 = 0;
		System.out.println("몇 개의 정수를 입력하시겠습니까?");
		int count = scan.nextInt();
		
		while(num <= count)
		{
			System.out.println("정수를 입력하세요");
			num2 = scan.nextInt();
			sum = sum + num2;
			num++;
		}
		System.out.println("입력하신 값들의 평균 값은 " +  ((double)sum / (double)count));
		*/
		int sum = 0;
		int num2 = 1;
		System.out.println("몇 개의 정수를 입력하시겠습니까?");
		int count = scan.nextInt();
		
		do
		{
			System.out.println("정수를 입력하세요");
			int num1 = scan.nextInt();
			sum = sum + num1;
			num2++;
		}while(num2 <= count);
		System.out.println("입력하신 값들의 평균 값은 " + ((double)sum / (double)count));
		

	}

}
