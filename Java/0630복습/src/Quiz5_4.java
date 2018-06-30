import java.util.Scanner;

public class Quiz5_4 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("첫번째 정수를 입력하세요");
		int num1 = s.nextInt();
		System.out.println("두번째 정수를 입력하세요");
		int num2 = s.nextInt();
		
		if(num1 > num2)
		{
			System.out.println("두 정수의 차는 " + (num1 - num2));
		}
		else
			System.out.println("두 정수의 차는 " + (num2 - num1));

	}

}
