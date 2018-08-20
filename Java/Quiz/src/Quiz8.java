import java.util.Scanner;
public class Quiz8 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int sum = 0;
		/*
		for(int i = 0; i < 5;)
		{
			System.out.println("정수를 입력하세요");
			int num = scan.nextInt();
			if(num < 1)
			{
				continue;
			}
			sum = sum + num;
			i++;
		}
		*/
		int i = 0;
		/*while(i < 5)
		{
			System.out.println("정수를 입력하세요");
			int num = scan.nextInt();
			if(num < 1)
			{
				continue;
			}
			sum = sum + num;
			i++;
		}
		*/
		do
		{
			System.out.println("정수를 입력하세요");
			int num = scan.nextInt();
			if(num < 1)
			{
				continue;
			}
			sum = sum + num;
			i++;
		}while(i < 5);
		System.out.println("정수들의 합은 "+sum);

	}

}
