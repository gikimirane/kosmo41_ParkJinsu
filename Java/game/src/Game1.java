import java.util.Random;
import java.util.Scanner;

public class Game1 {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		Scanner t = new Scanner(System.in);
		String[] cha1 = {"y","n"};
		int num2;
		for(;;)
		{
			int num1 = ran.nextInt(101);
			System.out.println("나는 지금부터 0부터 100사이의 숫자 중 하나를 생각하겠습니다");
			System.out.println("당신은 6회 안에 맞춰야 합니다");
			for(int i =0; i < 6; i++)
			{
				System.out.println("몇이라고 생각하는가 <0 to 100>");
				num2 = s.nextInt();
				if(num2 == num1)
				{
					System.out.println(num2+"는 정답입니다. 축하합니다!");
					break;
				}
				else if(num2 > num1)
				{
					System.out.println(num2 + "는 제가 생각한 숫자보다 큽니다.");
					System.out.println("당신의 기회는 " + "["+ (5-i)+ "]" + "남았습니다");
					continue;
				}
				else if(num2 < num1)
				{
					System.out.println(num2 + "는 제가 생각한 숫자보다 작습니다");
					System.out.println("당신의 기회는 " + "["+ (5-i)+ "]"+"남았습니다");
					continue;
				}
			}
			
			if(true)
			{
				System.out.println("게임이 끝났습니다");
				System.out.println();
				System.out.println("High & Low 게임을 플레이 해주셔서 감사합니다");
				System.out.println();
				System.out.println("다시 하시겠습니까? < y / n >");
				String cha = t.next();
				if(cha.equals(cha1[0]))
				{
					continue;
				}
				else if(cha.equals(cha1[1]))
				{
					System.out.println("게임을 종료합니다");
					break;
				}
				else 
				{
					System.out.println("잘못 입력하셨습니다");
					break;
				}			
			}

		}
	}

}
