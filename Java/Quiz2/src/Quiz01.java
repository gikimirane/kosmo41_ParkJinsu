import java.util.Scanner;

public class Quiz01 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum = 0;
		for(;;)
		{
			System.out.println("숫자를 입력하세요");
			int num = s.nextInt();
			sum = sum + num;
			if(num == 0)
			{
				System.out.println("숫자들의 합은 " + sum);
				break;
			}
		}

	}

}
