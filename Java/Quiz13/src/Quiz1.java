import java.util.Scanner;
public class Quiz1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] arr = new int[5];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println("수를 입력하세요");
			arr[i] = scan.nextInt();
		}
		int max = 0;
		int min = 999999999;
		int sum = 0;
		
		for(int i = 0; i < arr.length; i++)
		{
			if(max < arr[i])
			{
				max = arr[i];
			}
		}
		for(int i = 0; i < arr.length; i++)
		{
			if(min > arr[i])
			{
				min = arr[i];
			}
		}
		for(int e: arr)
		{
			sum += e;
		}
		System.out.println("최대값 = "+max);
		System.out.println("최소값 = "+min);
		System.out.println("합계 = "+sum);

	}

}
