import java.util.Scanner;

public class Quiz3 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println("숫자를 입력하세요");
			arr[i] = scan.nextInt();
					
		}
		System.out.println("홀수");
		for(int j = 0; j <arr.length; j++)
		{
			if((arr[j] % 2) == 1)
			{
				System.out.print(arr[j]);
				if(j < arr.length-2)
				{
					System.out.print(", ");
				}
			}
	
		}
		System.out.println();
		System.out.println("짝수");
		for(int j = 0; j <arr.length; j++)
		{
			if((arr[j] % 2) == 0)
			{
				System.out.print(arr[j]);
				if(j < arr.length-2)
				{
					System.out.print(", ");
				}
			}
	
		}
		

	}

}
