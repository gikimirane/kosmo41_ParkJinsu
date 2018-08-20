import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		System.out.println("두개의 정수를 입력하시오");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		/*if(num1 > num2)
		{
			min = num1 - num2;
			System.out.println("두 수의 차는 "+ min);
		}
		else
		{
			min = num2 - num1;
			System.out.println("두 수의 차는 "+ min);
		}
		*/
		/*System.out.println("두 수의 차는 " +(num1 > num2 ? num1 - num2 : num2 - num1));
	*/
		/*if(num1 > num2)
		{
			System.out.println("두 수의 차는 "+ (num1 - num2));
		}
		else
		{
			System.out.println("두 수의 차는 "+ (num2 - num1));
		}*/
		int min = num1 - num2;
		if(num1 > num2)
		{
			System.out.println("두 수의 차는 " + min);
		}
		else
		{
			System.out.println("두 수의 차는 " + (min * -1));
		}
		
	}
	


}
