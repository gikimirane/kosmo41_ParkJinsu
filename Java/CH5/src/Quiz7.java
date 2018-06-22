import java.util.Scanner;
public class Quiz7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("국어 점수를 입력하시오");
		int kor = scan.nextInt();
		System.out.println("영어 점수를 입력하시오");
		int eng = scan.nextInt();
		System.out.println("수학 점수를 입력하시오");
		int meth = scan.nextInt();
		int avg = (kor + eng + meth) / 3;
		
		if(avg >= 90)
		{
			System.out.println("당신의 학점은 A입니다");
		}
		else if(avg >= 80)
		{
			System.out.println("당신의 학점은 B입니다");
		}
		else if(avg >= 70)
		{
			System.out.println("당신의 학점은 C입니다");
		}
		else if(avg >= 50)
		{
			System.out.println("당신의 학점은 D입니다");
		}
		else 
		{
			System.out.println("당신의 학점은 F입니다");
		}
		

	}

}
