import java.util.Random;
import java.util.Scanner;

public class Quiz13_11 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random ran = new Random();
		String cha = new String();

		for(;;)
		{
			int min = 1;
			int max = 100;
			System.out.println("Pick a number between 1 and 100");
			System.out.println("내 숫자가 더 크면 h, 작으면 l, 맞추면 y");
			for(;;)
			{
				int mix = (max + min)/2;
				System.out.println("is it "+mix+"?");
				cha = s.next();
				if(cha.equalsIgnoreCase("h"))
				{
					min = mix;
				}
				if(cha.equalsIgnoreCase("l"))
				{
					max = mix;
				}
				if(cha.equalsIgnoreCase("y"))
				{
					System.out.println("정답입니다");
					break;
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
