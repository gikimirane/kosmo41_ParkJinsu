
public class Quiz10 {
//배열에 210, 19, 72, 129, 34  순서대로 데이터를 입력하고 데이터를 정렬하시오.
	public static void main(String[] args) {
		int[] arr = {210, 19, 72, 129, 34};
		int num = 0;
		for(int j = 0; j < arr.length-1; j++)
		{
			for(int i = 0; i < arr.length-1; i++)
			{
				if(arr[i] > arr[i+1])
				{
					num = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = num;
				}
			}
		}
		for(int e : arr)
		System.out.print(e+" ");
	}

}
