import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		System.out.println("�ΰ��� ������ �Է��Ͻÿ�");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		/*if(num1 > num2)
		{
			min = num1 - num2;
			System.out.println("�� ���� ���� "+ min);
		}
		else
		{
			min = num2 - num1;
			System.out.println("�� ���� ���� "+ min);
		}
		*/
		/*System.out.println("�� ���� ���� " +(num1 > num2 ? num1 - num2 : num2 - num1));
	*/
		/*if(num1 > num2)
		{
			System.out.println("�� ���� ���� "+ (num1 - num2));
		}
		else
		{
			System.out.println("�� ���� ���� "+ (num2 - num1));
		}*/
		int min = num1 - num2;
		if(num1 > num2)
		{
			System.out.println("�� ���� ���� " + min);
		}
		else
		{
			System.out.println("�� ���� ���� " + (min * -1));
		}
		
	}
	


}
