import java.util.Scanner;
public class Quiz8 {

	public static void main(String[] args) {
		int sum = 0;
		int a = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("5개의 정수를 입력하세요");
		while(a < 6)
		{
			int num1 = scan.nextInt();
			if(num1 < 1)
			{
				System.out.println("다시 입력 하세요");
				continue;
			}
			a++;
			sum = sum + num1;
		}
		System.out.println("5개의 정수의 합은 " + sum);
		
	}

}
