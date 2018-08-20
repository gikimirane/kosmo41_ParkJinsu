import java.util.Scanner;

public class Quiz5_2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("몇개의 정수를 입력하시겠습니까");
		int num1 = s.nextInt();
		int sum = 0;
		for(int i = 0;i < num1; i++)
		{
			System.out.println("정수를 입력하세요");
			int num2 = s.nextInt();
			sum = sum + num2;
		}
		System.out.println("모든 정수들의 평균값은 " +(float)(sum/num1));

	}

}
