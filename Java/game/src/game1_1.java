import java.util.Random;
import java.util.Scanner;

public class game1_1 {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		
		int num1;
		String cha1;
		while(true)
		{
			System.out.println("나는 지금 0 부터 100 까지의 숫자 중 하나를 생각하겠습니다.");
			System.out.println("당신은 그 숫자를 6회 안에 맞추면 됩니다.");
			num1 = ran.nextInt(101);
			int i = 5;
			while(i >= 0)
			{
				System.out.println("몇이라고 생각하십니까? <0 to 100>");
				int num2 = s.nextInt();
				if(num2 == num1)
				{
					System.out.println(num2 + "는 정답입니다. 축하합니다!");
					break;
				}
				if(num2 > num1)
				{
					System.out.println(num2 + "는 제가 생각한 숫자보다 큽니다");
				}				
				if(num2 < num1) 
				{
					System.out.println(num2 + "는 제가 생각한 숫자보다 작습니다");
				}
				if(i != 0) 
				{
					System.out.println("[ "+i+" ]"+ " 의 기회가 남아있습니다");
				}
				i--;
			}
			System.out.println("게임이 끝났습니다");
			System.out.println("High & Low 게임을 플레이 해주셔서 감사합니다.");
			System.out.println();
			System.out.println("다시 하시겠습니까? < y / n >");
			cha1 = s.next();
			if(cha1.equalsIgnoreCase("y"))
			{
				System.out.println("다시 시작합니다");
				continue;
			}
			else if(cha1.equalsIgnoreCase("n"))
			{
				System.out.println("Good bye");
				break;
			}
			else
			{
				System.out.println("잘못 입력하셨습니다");
				System.out.println("Bye");
				break;
			}
		}

	}

}
