import java.util.Random;
import java.util.Scanner;
public class Quiz9 {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		int num;
		String[] cha = {"","가위", "바위", "보"};
		System.out.println("무엇을 내시겠습니까? <1:가위, 2:바위, 3:보>");
		int a = ran.nextInt(3)+1;
		for(;;)
		{
			num = s.nextInt();
			if((0 < num) && ( num < 4))
			{
				break;
			}
			else
			{
				System.out.println("다시 입력하세요");
			}
		}
		System.out.println("사용자 : "+cha[num] + ", 컴퓨터 : "+ cha[a]);
		
		if(num == 1)
		{
			if(a == 1)
			{
				System.out.println("비겼습니다");
			}
			else if(a == 2)
			{
				System.out.println("졌습니다");
			}
			else
			{
				System.out.println("이겼습니다!");
			}
		}
		
		if(num == 2)
		{
			if(a == 1)
			{
				System.out.println("이겼습니다!");
			}
			else if(a == 2)
			{
				System.out.println("비겼습니다");
			}
			else
			{
				System.out.println("졌습니다");
			}
		}
		
		if(num == 3)
		{
			if(a == 1)
			{
				System.out.println("졌습니다");
			}
			else if(a == 2)
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
