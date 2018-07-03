import java.util.Random;

public class fsdafas {

	public static void main(String[] args) {
		Random ran = new Random();
		int[] arr = new int[9];
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = ran.nextInt(9)+1;	
		}
		for(int e : arr)
			System.out.print(e+" ");

	}

}
