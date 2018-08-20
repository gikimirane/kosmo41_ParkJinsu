import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println((i+1)+"번째 정수를 입력하세요");
			arr[i] = scan.nextInt();
		}
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] % 2 == 1)
			{
				System.out.print(arr[i]+" ");
			}
			
		}
		int min = 1;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] % 2 == 0)
			{
				System.out.print(arr[arr.length] + " ");
				min++;
			}
			
		}

	}

}
