import java.util.Scanner;
public class Quiz1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("ù ��° �� �Է�");
		int num1 = s.nextInt();
		
		System.out.println("�� ��° �� �Է�");
		int num2 = s.nextInt();
		
		System.out.println("��Ģ���� ���");
		System.out.println(num1 + " + " + num2 + " = " + (num1 + num2) );
		System.out.println(num1 + " - " + num2 + " = " + (num1 - num2) );
		System.out.println(num1 + " * " + num2 + " = " + (num1 * num2) );
		System.out.println(num1 + " / " + num2 + " = " + (num1 / num2) );
		System.out.println(num1 + " % " + num2 + " = " + (num1 % num2) );
	}

}
