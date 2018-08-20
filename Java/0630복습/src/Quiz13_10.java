import java.util.Random;
import java.util.Scanner;

public class Quiz13_10 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random ran = new Random();
		String cha = new String();
		
		for(;;)
		{
			System.out.println("나는 지금 0부터 100까지의 숫자 중 하나를 선택하겠습니다");
			System.out.println("당신은 6회 안에 맞추셔야 합니다");
			
			int num1 = ran.nextInt(101);
			for(int i = 0; i < 6; i++)
			{
				System.out.println("몇이라고 생각합니까 < 0 to 100 >");
				int num2 = s.nextInt();
				
				if(num1 == num2)
				{
					System.out.println(num2+"는 정답입니다! 축하합니다!");
					System.out.println();
					break;
				}
				if(num1 > num2)
				{
					System.out.println(num2+"는 제가 생각한 숫자보다 작습니다");
					System.out.println();
				}
				if(num1 < num2)
				{
					System.out.println(num2+"는 제가 생각한 숫자보다 큽니다");
					System.out.println();
				}
				if(!(5-i==0))
				{
					System.out.println("[ "+(5-i)+ " ] 의 기회가 남았습니다");
				}
			}
			System.out.println("게임이 끝났습니다.");
			System.out.println("다시 하시겠습니까? < y, n >");
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
