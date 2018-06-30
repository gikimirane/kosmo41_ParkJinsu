import java.util.Scanner;

public class Quiz5_7 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] cha = {"국어", "영어", "수학"};
		int sum = 0;
		
		for(int i = 0; i < cha.length; i++)
		{
			System.out.println(cha[i]+" 점수를 입력하세요");
			int num = s.nextInt();
			sum = sum + num;	 
		}
		int avg = sum / cha.length;
		
		if(avg >= 90)
		{
			System.out.println("당신의 학점은 "+avg+"점 이므로 A입니다");
		}
		else if(avg >= 80)
		{
			System.out.println("당신의 학점은 "+avg+"점 이므로 B입니다");
		}
		else if(avg >= 70)
		{
			System.out.println("당신의 학점은 "+avg+"점 이므로 C입니다");
		}
		else if(avg >= 50)
		{
			System.out.println("당신의 학점은 "+avg+"점 이므로 D입니다");
		}
		else
		{
			System.out.println("당신의 학점은 "+avg+"점 이므로 F입니다");
		}

	}

}
