import java.util.Random;
import java.util.Scanner;

public class RpsGame_Bigyo 
{
	void compare()
	{
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		for(;;)
		{
			System.out.print("무엇을 내시겠습니까? <1: 가위, 2: 바위, 3:보> : ");
			int num1 = s.nextInt();
			int num2 = ran.nextInt(3)+1;
			if(num1 == 1)
			{
				if(num2 == 1)
				{
					System.out.println("사용자 : 가위, 컴퓨터 : 가위");
					System.out.println("비겼습니다");
					break;
				}
				else if(num2 == 2)
				{
					System.out.println("사용자 : 가위, 컴퓨터 : 바위");
					System.out.println("졌습니다");
					break;
				}
				else if(num2 == 3)
				{
					System.out.println("사용자 : 가위, 컴퓨터 : 보");
					System.out.println("이겼습니다");
					break;
				}
			}
			else if(num1 == 2)
			{
				if(num2 == 1)
				{
					System.out.println("사용자 : 바위, 컴퓨터 : 가위");
					System.out.println("이겼습니다");
					break;
				}
				else if(num2 == 2)
				{
					System.out.println("사용자 : 바위, 컴퓨터 : 바위");
					System.out.println("비겼습니다");
					break;
				}
				else if(num2 == 3)
				{
					System.out.println("사용자 : 바위, 컴퓨터 : 보");
					System.out.println("졌습니다");
					break;
				}
			}
			else if(num1 == 3)
			{
				if(num2 == 1)
				{
					System.out.println("사용자 : 보, 컴퓨터 : 가위");
					System.out.println("졌습니다");
					break;
				}
				else if(num2 == 2)
				{
					System.out.println("사용자 : 보, 컴퓨터 : 바위");
					System.out.println("이겼습니다");
					break;
				}
				else if(num2 == 3)
				{
					System.out.println("사용자 : 보, 컴퓨터 : 보");
					System.out.println("비습니다");
					break;
				}
			}
			else
			{
				System.out.println("다시 입력하세요");
				continue;
			}

		}
		
	}
}