import java.util.Scanner;

public class Quiz11 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int num = s.nextInt();
		int mult = 1;
		while(num > 0)
		{
			System.out.print(num);
			if(num > 1)
			{
				System.out.print("x");
			}
			mult = mult * num;
			num--;
		}
		System.out.println("="+ mult);

	}

}
