import java.util.Scanner;
public class Quiz8 {

	public static void main(String[] args) {
		int sum = 0;
		int a = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("5���� ������ �Է��ϼ���");
		while(a < 6)
		{
			int num1 = scan.nextInt();
			if(num1 < 1)
			{
				System.out.println("�ٽ� �Է� �ϼ���");
				continue;
			}
			a++;
			sum = sum + num1;
		}
		System.out.println("5���� ������ ���� " + sum);
		
	}

}
