import java.util.Random;
import java.util.Scanner;

public class RpsGame {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		RpsGame_Bigyo comp = new RpsGame_Bigyo();
		int i = 0;
		
		for(;;)
		{
			comp.compare();
			System.out.print("다시 하시겠습니까?< Y / N >");
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
				System.out.println("잘못입력하셨습니다. 종료");
				break;
			}
				
		}

	}

}
