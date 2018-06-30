import java.util.Scanner;

public class Quiz5_11 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("수를 입력하세요");
		int num = s.nextInt();
		int multi = 1;
		while(num > 0)
		{
			System.out.print(num);
			if(num > 1)
			{
				System.out.print("x");
			}
			multi = multi * num;
			num--;
		}
		System.out.print("="+multi);

	}

}
