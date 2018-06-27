
public class Quiz6 {
	/*
	가로가 9, 세로가 3인 int형 2차원 배열을 선언하여 구구단 중 2, 3, 4단을 저장한다.
	그리고 제대로 저장이 되었는지 확인하기 위해 출력을 하는 프로그램을 작성하라
	*/
	public static void main(String[] args) {
		int[][] arr = new int[3][9];
		
		for(int j = 2; j < 5;)
		{
			for(int i = 0; i < arr.length; i++)
			{
				for(int k = 1; k < 10; k++)
				{
					arr[i][k-1] = j*k;
				}
				j++;
			}
		}
		
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr[i].length; j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
