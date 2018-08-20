
public class Quiz03 {

	public static void main(String[] args) {
		
		for(int i = 0; i < 100; i++)
		{
			if(((i+1) % 7 == 0) || ((i+1) % 9 == 0))
			{
				System.out.println(i+1);
			}
				
		}

	}

}
