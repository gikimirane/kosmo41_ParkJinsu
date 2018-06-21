import java.util.Scanner;
public class Quiz1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("첫 번째 수 입력");
		int num1 = s.nextInt();
		
		System.out.println("두 번째 수 입력");
		int num2 = s.nextInt();
		
		System.out.println("사칙연산 계산");
		System.out.println(num1 + " + " + num2 + " = " + (num1 + num2) );
		System.out.println(num1 + " - " + num2 + " = " + (num1 - num2) );
		System.out.println(num1 + " * " + num2 + " = " + (num1 * num2) );
		System.out.println(num1 + " / " + num2 + " = " + (num1 / num2) );
		System.out.println(num1 + " % " + num2 + " = " + (num1 % num2) );
	}

}
