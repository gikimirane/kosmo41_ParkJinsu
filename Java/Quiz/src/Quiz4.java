import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		System.out.println("�ΰ��� ������ �Է��ϼ���");
		int InPutNum1 = scan.nextInt();
		int InPutNum2 = scan.nextInt();
		
		if(InPutNum1 > InPutNum2)
		{
			System.out.println("�� ���� ���� " + (InPutNum1 - InPutNum2));
		}
		else
		{
			System.out.println("�� ���� ���� " + (InPutNum2 - InPutNum1));
		}
		*/
		System.out.println("�ΰ��� ������ �Է��ϼ���");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		System.out.println("�� ���� ���� "+ ((num1 > num2) ? (num1 - num2) : (num2 - num1)));

	}

}
