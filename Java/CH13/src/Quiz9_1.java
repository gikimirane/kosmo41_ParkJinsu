import java.util.Random;
import java.util.Scanner;
public class Quiz9_1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random ran = new Random();
		
		String[] cha = {"","가위","바위","보"};
		int num = ran.nextInt(3)+1;
		int a = 0;
		for(;;)
		{
			System.out.println("무엇을 내시겠습니까 <1.가위, 2.바위, 3.보>");
			a = s.nextInt();
			if((a > 0) && (a < 4))
			{
				System.out.println("사용자 : "+cha[a] + ", 컴퓨터 : "+cha[num]);
				break;
			}
			System.out.println("잘못 입력하셨습니다.");
		}
		if(a == 1)
		{
			if(num == 1)
			{
				System.out.println("비겼습니다");
			}
			else if(num == 2)
			{
				System.out.println("졌습니다");
			}
			else
			{
				System.out.println("이겼습니다");
			}
		}
		if(a == 2)
		{
			if(num == 1)
			{
				System.out.println("이겼습니다");
			}
			else if(num == 2)
			{
				System.out.println("비겼습니다");
			}
			else
			{
				System.out.println("졌습니다");
			}
		}
		if(a == 3)
		{
			if(num == 1)
			{
				System.out.println("졌습니다");
			}
			else if(num == 2)
			{
				System.out.println("이겼습니다");
			}
			else
			{
				System.out.println("비겼습니다");
			}
		}

	}

}
