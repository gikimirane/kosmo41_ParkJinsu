import java.util.Scanner;

public class Quiz5_6 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("구구단을 역순으로 출력 할 정수를 입력하세요");
		int num = s.nextInt();
		
		for(int i = 9; i > 0; i--)
		{
			System.out.println(num + "x" + i + "="+num*i);
		}

	}

}
