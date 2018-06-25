
public class Quiz14 {

	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 9; j>=0; j--)
			{
				if((((i*10)+j) + ((j*10)+i)) ==99)
				{
					System.out.println(" AZ");
					System.out.println("+ZA");
					System.out.println("------");
					System.out.println(((i*10)+j) + ((j*10)+i));
					System.out.println("A = "+ i + " Z = "+j);
				}
				continue;
			}
		}
	
	}

}
