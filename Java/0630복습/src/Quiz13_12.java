
public class Quiz13_12 {

	public static void main(String[] args) {
		int[] arr = {210,19,72,129,34};
		int num = 0;
		for(int i = 0; i < arr.length-1; i++)
		{
			for(int j = 0; j < arr.length-1; j++)
			{
				if(arr[j] > arr[j+1])
				{
					num = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = num;
				}
			}
		}
		for(int e : arr)
		{
			System.out.print(e+" ");
		}
	}

}
