import java.util.Arrays;

public class H8_ArraySearch {

	public static void main(String[] args) {
		int[] ar = { 33, 55, 11, 44, 22};
		Arrays.sort(ar); // 탐색 이전에 정렬이 선행되어야 한다
		for(int n : ar)
			System.out.print(n + "\t");
		System.out.println();
		
		int idx = Arrays.binarySearch(ar, 55);// 배열 ar에서 33을 찾아라
		System.out.println("Index of 33: " + idx);

	}

}
