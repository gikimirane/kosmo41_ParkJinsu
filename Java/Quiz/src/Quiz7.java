import java.util.Scanner;
public class Quiz7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		System.out.println("���� ������ �Է��ϼ���");
		int kor = scan.nextInt();
		System.out.println("���� ������ �Է��ϼ���");
		int Eng = scan.nextInt();
		System.out.println("���� ������ �Է��ϼ���");
		int meth = scan.nextInt();
		*/
		int sum = 0;
		/*
		for(int i = 0; i < 3; i++)
		{
			System.out.println("������ �Է��ϼ���");
			int num = scan.nextInt();
			sum = sum + num;
		}
		*/
		/*int i = 0;
		while(i < 3)
		{
			System.out.println("������ �Է��ϼ���");
			int num = scan.nextInt();
			sum = sum + num;
			i++;
		}
		*/
		int i = 0;
		do
		{
			System.out.println("������ �Է��ϼ���");
			int num = scan.nextInt();
			sum = sum + num;
			i++;
		}while(i < 3);
		int avg = sum / 3;
		if(avg >= 90)
		{
			System.out.println("����� ������ "+avg+ " �̹Ƿ� A�Դϴ�.");
		}
		else if(avg >= 80)
		{
			System.out.println("����� ������ "+avg+ " �̹Ƿ� B�Դϴ�.");
		}
		else if(avg >= 70)
		{
			System.out.println("����� ������ "+avg+ " �̹Ƿ� C�Դϴ�.");			
		}
		else if(avg >= 50)
		{
			System.out.println("����� ������ "+avg+ " �̹Ƿ� D�Դϴ�.");
		}
		else
		{
			System.out.println("����� ������ "+avg+ " �̹Ƿ� F�Դϴ�.");
		}
	}

}
