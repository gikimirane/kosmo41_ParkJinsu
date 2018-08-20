
public class Quiz6 {

	public static void main(String[] args) {
		int[][] arr = new int[3][9];
		int k = 0;
		for(int i = 2; i < 5; i++)
		{
			for(int j = 1; j < 10; j++)
			{
				arr[k][j-1] = i * j;
			}
			k++;
		}
		for(int i = 0; i <arr.length; i++)
		{
			for(int j = 0; j < arr[i].length; j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

	}

}
