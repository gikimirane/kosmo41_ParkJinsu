import java.util.Random;
import java.util.Scanner;

public class BaseBall {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		String cha = new String();
		System.out.println("숫자로 시작하는 야구게임");
		for(;;)
		{
			int i = 0;
			int num1 = ran.nextInt(9)+1;
			int num2 = ran.nextInt(9)+1;
			int num3 = ran.nextInt(9)+1;
			if((num1 != num2) && (num2 != num3) && (num1 != num3))
			{
				for(i = 1;;i++)
				{
					int x = 0, y = 0;
					System.out.println("3개의 숫자를 입력하세요."+"< "+i+" >"+"회");
					int cost=s.nextInt();
					int cost1 = cost / 100;
					int cost2 = (cost-(cost1*100)) / 10;
					int cost3 = cost % 10;
					if(cost1 == num1)
					{
						x++;
					}
					else if((cost1 == num2) || (cost1 == num3))
					{
						y++;
					}
			
					if(cost2 == num2)
					{
						x++;
					}
					else if((cost2 == num1) || (cost2 == num3))
					{
						y++;
					}
		
					if(cost3 == num3)
					{
						x++;
					}
					else if((cost3 == num1) || (cost3 == num2))
					{
						y++;
					}
					System.out.println(cost1 + ":" + cost2 + ":" + cost3);
					System.out.println(x + " Strike, " + y + " Ball");
					if(x == 3)
					{
						System.out.println("You Win!!");
						System.out.println();
						System.out.println("다시 하시겠습니까? < y / n >");
						break;
					}
					
				}
				cha = s.next();
				if(cha.equalsIgnoreCase("y"))
				{
					
					continue;
				}
				else if(cha.equalsIgnoreCase("n"))
				{
					System.out.println("Good game");
					break;
				}
				else
				{
					System.out.println("잘못입력하셨습니다");
					break;
				}
				
			}
		}
	}

}
