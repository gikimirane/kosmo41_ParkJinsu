import java.util.Scanner;

public class Quiz5_8 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum = 0;
		for(int i = 1; i < 6;)
		{
			System.out.println(i + "번째 숫자를 입력하세요");
			int num = s.nextInt();
			if(num < 1)
			{
				System.out.println("0보다 큰 숫자를 입력하세요");
				continue;
			}
			i++;
			sum = sum + num;
		}
		System.out.println("모든 정수의 합은 " + sum);

	}

}
