
public class Quiz11 {

	public static void main(String[] args) {
		int[][] arr = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
		};
		int[][] arr2 = new int[4][4];

		for(int i = 0; i < arr.length; i++)
		{
			for(int k = 0; k < 4; k++)
			{
				for(int j = 0; j < 4; j++)
				{				
					arr2[j][3-k] = arr[k][j];
					
				}
			}
			
			for(int l = 0; l < arr2.length; l++)
			{
				for(int m = 0; m <arr2.length; m++)
				{
					System.out.print(arr2[l][m] + " ");
				}
				System.out.println();				
			}
			System.out.println();
			if(true)
			{
				for(int l = 0; l < arr.length; l++)
				{
					for(int m = 0; m < arr2.length; m++)
					{
						arr[l][m] = arr2[l][m];
					}
				}
			}
		}

	}

}
