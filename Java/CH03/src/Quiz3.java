import java.util.Scanner;

public class Quiz3 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("�� ���� ������ �Է��Ͻÿ�");
		int num1 = s.nextInt();
		int num2 = s.nextInt();
		int result = num1 / num2;
		int result2 = num1 % num2;
		System.out.println(num1 + " ������ " + num2 + " �� ���� " + result+ " �Դϴ�.");
		System.out.println(num1 + " ������ " + num2 + " �� �������� " + result2 + " �Դϴ�.");

	}

}
