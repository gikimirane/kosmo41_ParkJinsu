import java.util.Random;
import java.util.Scanner;

public class HighLow {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		HighLow_2 game = new HighLow_2();
		
		for(;;)
		{
			game.play();
			System.out.println("게임을 플레이 해주셔서 감사합니다");
			System.out.println("다시 하시겠습니까?< Y / N >");
			String str = s.next();
			if(str.equals("y"))
			{
				continue;
			}
			else if(str.equals("n"))
			{
				System.out.println("Good Game!");
				break;
			}
			else
			{
				System.out.println("잘못 누르셨습니다. 종료");
				break;
			}
		}

	}

}
