import java.util.Scanner;

public class Quiz06 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("수를 입력하세욧");
		int num = s.nextInt();
		
		for(int i = 9; i > 0; i--)
		{
			System.out.println(num + "x" + i + "="+(num*i));
		}

	}

}
