
public class TwoDimenArray {

	public static void main(String[] args) {
		int[][] arr = new int[3][4];
		int num = 1;
		
		//배열에 값을 저장
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr[i].length; j++)
			{
				arr[i][j] = num;
				num++;
			}
		}
		
		//배열에 저장된 값을 출력
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr[i].length; j++)
			{
				System.out.print(arr[i][j] +"\t");
			}
			System.out.println();
		}

	}

}
