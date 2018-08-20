
public class Quiz13_13 {

	public static void main(String[] args) {
		int[][] arr1 = {
			{1,2,3,4},
			{5,6,7,8},
			{9,10,11,12},
			{13,14,15,16}
		};
		int[][] arr2 = new int[4][4];
		for(int k = 0; k < 3; k++)
		{
			for(int i = 0; i < arr1.length;i++)
			{
				for(int j = 0; j < arr2.length; j++)
				{
					arr2[j][arr2.length-1-i] = arr1[i][j]; 
				}
			}
			for(int i = 0; i < arr2.length; i++)
			{
				for(int j = 0; j < arr2.length; j++)
				{
					arr1[i][j] = arr2[i][j];
				}
			}
			for(int i = 0; i < arr1.length; i++)
			{
				for(int j = 0; j < arr1.length; j++)
				{
					System.out.print(arr1[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}

	}

}
