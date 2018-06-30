import java.util.Random;
import java.util.Scanner;

public class Quiz13_14 {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		String cha = new String();
		System.out.println("숫자로 시작하는 야구게임!");
		for(;;)
		{
			int cost1 = ran.nextInt(9)+1;
			int cost2 = ran.nextInt(9)+1;
			int cost3 = ran.nextInt(9)+1;
			if((cost1 != cost2) && (cost2 != cost3) && (cost1 != cost3))
			{
				for(int i = 1;;i++)
				{
					int a = 0;
					int b = 0;
					System.out.println("세자리 숫자를 입력하세요 ("+i+")회");
					int num = s.nextInt();
					int num1 = num/100;
					int num2 = (num-(num1*100))/10;
					int num3 = num % 10;
					System.out.println(num1+":"+num2+":"+num3);
					
					if(num1 == cost1)
					{
						a++;
					}
					else if((num1 == cost2) || (num1 == cost3))
					{
						b++;
					}
					if(num2 == cost2)
					{
						a++;
					}
					else if((num2 == cost1) || (num2 == cost3))
					{
						b++;
					}
					if((num3 == cost3))
					{
						a++;
					}
					else if((num3 == cost1) || (num3 == cost2))
					{
						b++;
					}
					if(a == 3)
					{
						System.out.println("You win!!");
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
					else
					{
						System.out.println(a+" Strike, "+b+" Ball");
					}
					
					
				}
			}
			
		}

	}

}
