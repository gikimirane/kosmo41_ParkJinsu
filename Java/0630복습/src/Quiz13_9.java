import java.util.Random;
import java.util.Scanner;

public class Quiz13_9 {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		String cha = new String();
		for(;;)
		{
			int num1 = ran.nextInt(3)+1;
			
			System.out.println("무엇을 내시겠습니까? < 1.가위, 2.바위, 3.보 >");
			int num2 = s.nextInt();
			if(num2 == 1)
			{
				if(num1 == 1)System.out.println("비겼습니다"+ "\n"+"사용자 : 가위, 컴퓨터 : 가위");
				if(num1 == 2)System.out.println("졌습니다."+"\n"+"사용자 : 가위, 컴퓨터 : 바위");
				if(num1 == 3)System.out.println("이겼습니다."+"\n"+"사용자 : 가위, 컴퓨터 : 보");
			}
			else if(num2 == 2)
			{
				if(num1 == 1)System.out.println("이겼습니다"+ "\n"+"사용자 : 바위, 컴퓨터 : 가위");
				if(num1 == 2)System.out.println("비겼습니다."+"\n"+"사용자 : 바위, 컴퓨터 : 바위");
				if(num1 == 3)System.out.println("졌습니다."+"\n"+"사용자 : 바위, 컴퓨터 : 보");
			}
			else if(num2 == 3)
			{
				if(num1 == 1)System.out.println("졌습니다"+ "\n"+"사용자 : 보, 컴퓨터 : 가위");
				if(num1 == 2)System.out.println("이겼습니다."+"\n"+"사용자 : 보, 컴퓨터 : 바위");
				if(num1 == 3)System.out.println("비겼습니다."+"\n"+"사용자 : 보, 컴퓨터 : 보");
			}
			else
			{
				System.out.println("잘못 누르셨습니다");
				continue;
			}
			System.out.println("게임이 끝났습니다.");
			System.out.println("다시 하시겠습니까? < y, n>");
			cha = s.next();
			if(cha.equalsIgnoreCase("y"))
			{
				continue;
			}
			else if(cha.equalsIgnoreCase("n"))
			{
				System.out.println("good game");
				break;
			}
			else
			{
				System.out.println("잘못 누르셨습니다");
				break;
			}
		}

	}

}
