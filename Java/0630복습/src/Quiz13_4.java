import java.util.Scanner;

public class Quiz13_4 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int[] arr = new int[10];
		int j = 0;
		int k = 0;
		for(int i = 0; i < arr.length;i++)
		{
			System.out.println((i+1)+"번째 정수를 입력하세요");
			int num = s.nextInt();
			if(num % 2 == 1)
			{
				arr[k] = num;
				k++;
			}
			if(num % 2 == 0)
			{
				arr[arr.length-1-j] = num;
				j++;
			}
			
		}
		for(int e : arr)
		{
			System.out.print(e+" ");
		}

	}

}
