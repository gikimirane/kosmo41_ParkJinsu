import java.util.Scanner;

class Max
{
	int a = 0;
	int up(int[] num)
	{
		for(int i = 0; i < num.length; i++)
		{
			if(a < num[i])
			{
				a = num[i];
			}
		}
		return a;
	}
	
}
class Min
{
	int down(int[] num)
	{
		int a = 1000000;
		for(int i = 0; i < num.length; i++)
		{
			if(a > num[i])
			{
				a = num[i];
			}
		}
		return a;
		
	}
}

class sum
{
	int hap(int[] num)
	{
		int sum = 0;
		for(int e: num)
		{
			sum += e;
		}
		return sum;
	}
}

public class Quiz1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Max MaxNum = new Max();
		Min MinNum = new Min();
		sum sumnum = new sum();
		int[] num = new int[5];
		
		for(int i = 0; i < num.length; i++)
		{
			System.out.println("숫자를 입력하세요");
			num[i] = scan.nextInt();
		}
		System.out.println("최대값 = "+MaxNum.up(num));
		System.out.println("최소값 = "+MinNum.down(num));
		System.out.println("모든 배열의 합 = "+sumnum.hap(num));
		

	}

}
