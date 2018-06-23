import java.util.Scanner;
public class Quiz7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		System.out.println("국어 점수를 입력하세요");
		int kor = scan.nextInt();
		System.out.println("영어 점수를 입력하세요");
		int Eng = scan.nextInt();
		System.out.println("수학 점수를 입력하세요");
		int meth = scan.nextInt();
		*/
		int sum = 0;
		/*
		for(int i = 0; i < 3; i++)
		{
			System.out.println("점수를 입력하세요");
			int num = scan.nextInt();
			sum = sum + num;
		}
		*/
		/*int i = 0;
		while(i < 3)
		{
			System.out.println("점수를 입력하세요");
			int num = scan.nextInt();
			sum = sum + num;
			i++;
		}
		*/
		int i = 0;
		do
		{
			System.out.println("점수를 입력하세요");
			int num = scan.nextInt();
			sum = sum + num;
			i++;
		}while(i < 3);
		int avg = sum / 3;
		if(avg >= 90)
		{
			System.out.println("당신의 학점은 "+avg+ " 이므로 A입니다.");
		}
		else if(avg >= 80)
		{
			System.out.println("당신의 학점은 "+avg+ " 이므로 B입니다.");
		}
		else if(avg >= 70)
		{
			System.out.println("당신의 학점은 "+avg+ " 이므로 C입니다.");			
		}
		else if(avg >= 50)
		{
			System.out.println("당신의 학점은 "+avg+ " 이므로 D입니다.");
		}
		else
		{
			System.out.println("당신의 학점은 "+avg+ " 이므로 F입니다.");
		}
	}

}
