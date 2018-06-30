import java.util.Scanner;
class Cal1
{
	void mix(int arr[])
	{
		int sum = 0;
		int max = 0;
		int min = 9999999;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] > max)
			{
				max = arr[i];
			}
			if(arr[i] < min)
			{
				min = arr[i];
			}
			sum = sum + arr[i];
		}
		System.out.println("최대값 : "+max);
		System.out.println("최소값 : "+min);
		System.out.println("모든 수의 합 : " + sum);
	}
	/*int hap(int arr[])
	{
		int sum = 0;
		for(int i = 0; i <arr.length; i++)
		{
			sum = sum + arr[i];
		}
		return sum;
	}
	int Max(int arr[])
	{
		int max = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] > max)
			{
				max = arr[i];
			}
		}
		return max;
	}
	
	int Min(int arr[])
	{
		int min = 9999999;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] < min)
			{
				min = arr[i];
			}
		}
		return min;
	}*/
}
public class Quiz13_1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] arr = new int[5];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println("정수를 입력하세요");
			int num = s.nextInt();
			arr[i] = num;
		}
		Cal1 cal1 = new Cal1();
		cal1.mix(arr);

	}

}
