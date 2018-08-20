import java.util.InputMismatchException;
import java.util.Scanner;
public class ExceptionCase5 {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		try
		{
			System.out.print("a/b...a? ");
			int n1 = kb.nextInt();
			
			System.out.print("a/b...b? ");
			int n2 = kb.nextInt();
			
			System.out.printf("%d / %d = %d \n", n1, n2, n1 / n2);
			System.out.println("Good bye~~~!");
		}
		catch(ArithmeticException | InputMismatchException e)
		{
			if(e.getMessage().equalsIgnoreCase("/ by zero"))
			{
				System.out.println("잘못 입력하셨습니다");
			}
		}
		
		System.out.println("Good bye~~~~!");

	}

}
