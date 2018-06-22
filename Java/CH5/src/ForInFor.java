
public class ForInFor {

	public static void main(String[] args) {
		/*for(int i = 0; i < 3; i++)
		{
			System.out.println("-----------------------");
			System.out.println("i"+" = "+i);
			for(int j = 0; j < 3; j++)
			{
				System.out.println("      "+"j" + " = " + j);
			}
			
		}
		*/
		 
		  /*for(int i = 2; i < 10; i++)
		 {
		  		for(int j = 1; j < 10; j++)
		 		{
		 			System.out.println(" "+i + " * " + j + " = " + (i * j));
		 		}	
		  		
		  }
		 */
		int i = 2;
		
		
		while(i < 10)
		{
			int j = 1;
			while(j < 10)
			{
				System.out.println(i + " * " + j + " = " + (i * j));
				j++;
			}
			i++;
			
		}
	
	}

}
