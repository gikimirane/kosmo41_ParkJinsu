import java.util.Scanner;
public class Quiz6 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		for(int num2 = 9; num2 >= 1; num2--)
		{
			System.out.println(num1 + "x" + num2 + " = " + (num1 * num2));
		}
		

	}

}
