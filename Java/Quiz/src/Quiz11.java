import java.util.Scanner;
public class Quiz11 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int multi = 1;
		System.out.println("정수를 입력하세요");
		int num = scan.nextInt();
		/*
		for(; num > 0; num--)
		{
			System.out.print(num);
			if(num > 1)
			{
				System.out.print("X");
			}
			multi = multi * num;
		}
		System.out.print("="+multi);
		*/
		/*while(num > 0)
		{
			System.out.print(num);
			if(num > 1)
			{
				System.out.print("X");
			}
			multi = multi * num;
			num--;
		}
		System.out.println("="+multi);
*/
		do
		{
			System.out.print(num);
			if(num > 1)
			{
				System.out.print("X");
			}
			multi = multi * num;
			num--;
		}while(num > 0);
		System.out.println("="+multi);
	}
	

}
