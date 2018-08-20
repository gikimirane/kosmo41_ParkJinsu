
public class Quiz13_6 {

	public static void main(String[] args) {
		int[][] arr = new int[3][9];
		int k = 2;
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr[i].length; j++)
			{
				arr[i][j] = k * (j+1);
			}
			k++;
		}
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j< arr[i].length; j++)
			{
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	

	}

}
