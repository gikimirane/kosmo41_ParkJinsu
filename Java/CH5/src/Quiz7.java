import java.util.Scanner;
public class Quiz7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("���� ������ �Է��Ͻÿ�");
		int kor = scan.nextInt();
		System.out.println("���� ������ �Է��Ͻÿ�");
		int eng = scan.nextInt();
		System.out.println("���� ������ �Է��Ͻÿ�");
		int meth = scan.nextInt();
		int avg = (kor + eng + meth) / 3;
		
		if(avg >= 90)
		{
			System.out.println("����� ������ A�Դϴ�");
		}
		else if(avg >= 80)
		{
			System.out.println("����� ������ B�Դϴ�");
		}
		else if(avg >= 70)
		{
			System.out.println("����� ������ C�Դϴ�");
		}
		else if(avg >= 50)
		{
			System.out.println("����� ������ D�Դϴ�");
		}
		else 
		{
			System.out.println("����� ������ F�Դϴ�");
		}
		

	}

}
