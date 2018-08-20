import java.util.Random;
import java.util.Scanner;

public class HighLow_2 {
	
	void play()
	{
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		Compare com = new Compare();
		System.out.println("나는 지금 0 부터 100 사이의 값 중에 하나를 생각하겠습니다");
		System.out.println("당신은 그 숫자를 6회안에 맞추시면 됩니다");
		System.out.println();
		int num1 = ran.nextInt(101);
		for(int i = 1; i <= 7; i++)
		{
			System.out.print("몇이라고 생각합니까?<0 to 100 > ");
	
			int num2 = s.nextInt();
			if(com.compare(num1, num2, 6-i) == true)
			{
				break;
			}
		}
	}

}
