import java.util.Scanner;
abstract class mix
{
	Scanner s = new Scanner(System.in);
	int sum = 0;
	abstract void hap();
	void newprint()
	{
		System.out.println("모든 정수의 합은 " + sum);
	}
}
class hap extends mix
{

	void hap ()
	{
		

		for(;;)
		{
			System.out.println("정수를 입력하세요");
			int num = s.nextInt();
			sum = sum + num;
			if(num == 0)
			{
				break;
			}
		}
	}
}

public class Quiz5_1 {
//사용자로부터 계속해서 정수를 입력받는다.
//단 0을 입력받게되면 기존에 입력받은 모든 정수를 더한후 결과를 출력하는 프로그램을 작성하시오.

	public static void main(String[] args) {
		
		hap num = new hap();
		num.hap();
		num.newprint();

	}

}
