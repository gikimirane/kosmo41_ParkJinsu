import java.util.Scanner;
public class Quiz8 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] cha = {"국어", "영어", "수학", "국사"};
		int[][] arr = new int[4][4];
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println("4명의 "+cha[i]+" 점수를 입력하세요");
			for(int j = 0; j < arr[i].length; j++)
			{
				arr[i][j] = s.nextInt();
			}
		}
		int sum = 0;
		System.out.print("구분"+"\t"+"순신"+'\t'+"감찬"+'\t'+"문덕"+"\t"+"권율"+'\t'+"총점");
		System.out.println();
		for(int i = 0; i < arr.length; i++)
		{
			sum = 0;
			System.out.print(cha[i]+"\t");
			for(int j = 0; j < arr.length; j++)
			{
				System.out.print(arr[i][j] + "\t");
				sum = sum + arr[i][j];
			}
			System.out.println(sum);
		}
		
		System.out.print("총점"+"\t");
		for(int i = 0; i <arr.length; i++)
		{
			sum = 0;
			for(int j = 0; j < arr.length; j++ )
			{
				sum = sum + arr[j][i];
			}
			System.out.print(sum+"\t");
		}

	}

}
