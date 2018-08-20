import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		System.out.println("두개의 정수를 입력하세요");
		int InPutNum1 = scan.nextInt();
		int InPutNum2 = scan.nextInt();
		
		if(InPutNum1 > InPutNum2)
		{
			System.out.println("두 수의 차는 " + (InPutNum1 - InPutNum2));
		}
		else
		{
			System.out.println("두 수의 차는 " + (InPutNum2 - InPutNum1));
		}
		*/
		System.out.println("두개의 정수를 입력하세요");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		System.out.println("두 수의 차는 "+ ((num1 > num2) ? (num1 - num2) : (num2 - num1)));

	}

}
