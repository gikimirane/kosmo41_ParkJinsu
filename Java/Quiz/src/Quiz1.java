import java.util.Scanner;

public class Quiz1 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		/*
		int sum = 0;
		
		for(int num = 1; num != 0;)
		{
			System.out.println("������ �Է��Ͻÿ�");
			num = scan.nextInt();
			sum = sum + num;
		}
		System.out.println("�Է��� �������� ���� " + sum);
		*/
		
		int sum = 0;
		int num = 1;
		while(num != 0)
		{
			System.out.println("������ �Է��Ͻÿ�");
			num = scan.nextInt();
			sum = sum + num;
		}
		System.out.println("�Է��� �������� ���� " + sum);
		
		/*
		int x = 0;
		int sum = 0;
		int num = 0;
		
		do
		{
			System.out.println("������ �Է��ϼ���");
			num = scan.nextInt();
			sum = sum + num;
			x++;
		}while(num != 0);
		System.out.println("sum : "+ sum);
		*/
	}
	

}
