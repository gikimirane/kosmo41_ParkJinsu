import java.util.Scanner;
public class Quiz4_1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println("숫자를 입력하세요");
			arr[i] = scan.nextInt();
		}
		System.out.print("입력한 숫자 : ");
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i]);
			if(arr.length-1 > i)
			{
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.print("출력한 숫자 : ");
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i]%2 == 1)
			{
				System.out.print(arr[i]);
				if(arr.length-1 > i)
				{
					System.out.print(", ");
				
				}
		
			}

		}
		
		for(int i = arr.length-1; i >= 0; i--)
		{
			if(arr[i] % 2 ==0)
			{
				
				 System.out.print(arr[i] + " ");
				 if(arr.length-1 > 0)
					{
						System.out.print(", ");
					
					}
				
			}
		}

	}

}
