import java.util.Scanner;
public class Quiz6 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		System.out.println("정수를 입력하시오");
		int num = scan.nextInt();
		/*
		for(int i = 9; i > 0; i--)
		{
			System.out.println(num+"X"+i+"="+(num*i));
		}
		*/
		/*
		int i = 9;
		while(i > 0)
		{
			System.out.println(num+"X"+i+"="+(num*i));
			i--;
		}
		*/
		int i = 9;
		do
		{
			System.out.println(num+"X"+i+"="+(num*i));
			i--;
		}while(i > 0);
	}

}
