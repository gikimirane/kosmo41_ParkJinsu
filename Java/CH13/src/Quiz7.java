
public class Quiz7 {

	public static void main(String[] args) {
		int[][] arr1 = {
				{1,2,3,4},
				{5,6,7,8}
		};
		
		int[][] arr2 = new int[4][2];
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				arr2[i][j] = arr1[j][i];
			}
		}
		for(int i = 0; i < arr2.length; i++)
		{
			for(int j = 0; j < arr2[i].length; j++)
			{
				System.out.print(arr2[i][j]+" ");
			}
			System.out.println();
		}

	}

}
