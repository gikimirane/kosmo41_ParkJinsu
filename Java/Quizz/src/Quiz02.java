import java.util.Scanner;

public class Quiz02 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum = 0;
		System.out.println("숫자를 입력하세요");
		int num1 = s.nextInt();
		for(int i = 0; i < num1; i++)
		{
			System.out.println("정수를 입력하세요");
			int num2 = s.nextInt();
			sum = sum + num2;
		}
		System.out.println("정수들의 평균 값은 " + (double)sum/(double)num1);
		

	}

}
