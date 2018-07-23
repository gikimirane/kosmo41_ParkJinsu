import java.util.Scanner;

public class Quiz08 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int sum = 0;
		for(int i = 0; i < 5;)
		{
			System.out.println("정수를 입력하세요");
			int num = s.nextInt();
			if(num < 1)
			{
				System.out.println("다시 입력하세욧");
				continue;
			}
			sum = sum + num;
			i++;
		}
		System.out.println("모든 수의 합은 " + sum);

	}

}
