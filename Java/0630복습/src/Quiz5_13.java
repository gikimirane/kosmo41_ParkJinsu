
public class Quiz5_13 {

	public static void main(String[] args) {
		System.out.println("가로");
		for(int i = 1; i < 10; i++)
		{
			for(int j = 2; j < 10; j++)
			{
				System.out.print(j + "x" + i + "="+(j*i)+"\t");
			}
			System.out.println();
		}
		System.out.println("세로");
		for(int i = 2; i < 10; i++)
		{
			for(int j = 1; j < 10; j++)
			{
				System.out.print(i + "x" + j + "="+(i*j)+"\t");
			}
			System.out.println("\n");
		}

	}

}
