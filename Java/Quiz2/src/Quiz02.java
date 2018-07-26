import java.util.Scanner;

public class Quiz02 {

	public static void main(String[] args) {
		int[] arr = new int[10];
		Scanner s = new Scanner(System.in);
		int j = 9;
		int k = 0;
		for(int i =0; i < arr.length;i++)
		{
			System.out.println("정수를 입력하세요");
			int num = s.nextInt();
			if(num % 2 == 1)
			{
				arr[k] = num;
				k++;
			}
			else
			{
				arr[j] = num;
				j--;
				if(j < 0)
					break;
			}
		}
		for(int e : arr)
		{
			System.out.print(e+" ");
		}

	}

}
