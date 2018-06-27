
public class Quiz7 {

	public static void main(String[] args) {
		int[][] arr = {
				{1,2,3,4},
				{5,6,7,8}
		};
		int[][] arr1 = new int[4][2];
		for(int i = 0; i < arr1.length; i++)
		{
			for(int j = 0; j <arr1[i].length; j++)
			{
				arr1[i][j] = arr[j][i];
				System.out.print(arr1[i][j]+" ");
			}
			System.out.println();
		}
	

	}

}
