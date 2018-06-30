import java.util.Scanner;

public class Quiz13_3 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println((i+1)+"번째 정수를 입력하세요");
			arr[i] = s.nextInt();
		}
		System.out.print("짝수 : ");
		for(int i = 0; i < arr.length; i++ )
		{
			if(arr[i] % 2 == 0)
			{
				System.out.print(arr[i] + " ");
			}
		}
		System.out.println();
		System.out.print("홀수 : ");
		for(int i = 0; i <arr.length; i++)
		{
			if(arr[i] % 2 == 1)
			{
				System.out.print(arr[i]+" ");
			}
		}

	}

}
