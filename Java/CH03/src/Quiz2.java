import java.util.Scanner;

public class Quiz2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("제곱 할 수를 입력하시오.");
		int num = s.nextInt();
		
		System.out.println(num + "의 제곱은 " + (num * num));

	}

}
