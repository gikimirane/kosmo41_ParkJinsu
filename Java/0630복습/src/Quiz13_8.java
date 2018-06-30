import java.util.Scanner;

public class Quiz13_8 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[][] arr = new int[5][5];
		String[] cha = {"국어", "영어", "수학", "국사","총점"};
		
		for(int i = 0; i < arr.length-1; i++)
		{
			int sum = 0;
			System.out.println("4명의 " + cha[i]+" 점수를 입력하세요");
			for(int j = 0; j < arr[i].length-1; j++)
			{
				arr[i][j] = s.nextInt();
				sum = sum + arr[i][j];
			}
			arr[i][arr.length-1] = sum;	
		}
		for(int i = 0; i < arr.length-1; i++)
		{
			int hap = 0;
			for(int j = 0; j < arr.length-1; j++)
			{
				hap = hap + arr[j][i];
			}
			arr[arr.length-1][i] = hap;
		}
		
		System.out.print("구분"+"\t"+"순신"+"\t"+"감찬"+"\t"+"문덕"+"\t"+"권율"+"\t"+"총점");
		System.out.println();
		for(int i = 0; i < arr.length;i++)
		{
			System.out.print(cha[i]+"\t");
			for(int j = 0; j < arr[i].length; j++)
			{
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
		
		

	}

}
