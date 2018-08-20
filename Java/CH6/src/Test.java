
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum=0;
		int num;
		do {
			System.out.println("숫자를 입력하시오.");
			num = s.nextInt();
			sum = sum + num;
		}while(num != 0);
		
		System.out.println("입력한 수의 합은 "+ sum);
				
	}

}
