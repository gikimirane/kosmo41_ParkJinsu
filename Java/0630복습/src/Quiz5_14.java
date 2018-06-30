
public class Quiz5_14 {

	public static void main(String[] args) {
		for(int a = 0; a < 10; a++)
		{
			for(int z = 0; z < 10; z++)
			{
				if(a + z == 9)
				{
					System.out.println(" AZ");
					System.out.println("+ZA");
					System.out.println("------");
					System.out.println((a*10 + z) + (z*10 + a));
					System.out.println("A = "+ a + ", Z = "+ z);
					break;
				}
			}
		}

	}

}
