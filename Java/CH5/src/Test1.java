import java.util.Scanner;
public class Test1 {

	public static void main(String[] args) {
		
		int sum = 0;
	
		Scanner a = new Scanner(System.in);
		System.out.println("Ƚ���� �Է��Ͻÿ�");
		int num1 = a.nextInt();

		/*while(num2 <= num1)
		{
			System.out.println("���� �Է��Ͻÿ�");
			score = a.nextInt();
			sum = sum + score;
			num2++;
		}
		*/
		for(int num2 = 1; num2 <= num1; num2++)
		{
			System.out.println("���� �Է��Ͻÿ�");
			int score = a.nextInt();
			
			sum = sum + score;
		}
		double avg = (double)sum / (double)num1;
		System.out.println("�Է��Ͻ� ������ ��� ���� " + avg + "�Դϴ�");
		
		/*do
		{
			System.out.println("���� �Է��Ͻÿ�");
			int score = a.nextInt();
			sum = sum + score;
			num2++;
		}while(num2 <= num1);
		double avg = (double)sum / (double)num1;
		System.out.println("�Է��Ͻ� ���� ����� "+ avg);	
*/
	}

}
