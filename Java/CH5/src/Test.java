
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum=0;
		int num;
		do {
			System.out.println("���ڸ� �Է��Ͻÿ�.");
			num = s.nextInt();
			sum = sum + num;
		}while(num != 0);
		
		System.out.println("�Է��� ���� ���� "+ sum);
				
	}

}
