import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println("숫자 입력");
			arr[i] = scan.nextInt();
		}
		int[] brr = new int[arr.length]; // 홀수를 담을 배열을 추가 
		for(int i = 0; i < arr.length; i++)
		{
			if((arr[i] % 2) == 1)// 홀수를 추출하여 brr배열에 저장한다.
			{
				brr[i] = arr[i];
				System.out.print(brr[i]+" ");
			}
		}
		
		int[] crr = new int[arr.length];// 짝수를 담을 배열을 추가
		for(int i = arr.length-1; i >= 0; i--)
		{
			if((arr[i] % 2) == 0)//짝수를 추출하여 crr배열에 저장한다
			{
				crr[i] = arr[i];
				System.out.print(crr[i]+" ");
			}
		}
		
		

	}

}
