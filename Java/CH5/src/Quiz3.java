
public class Quiz3 {

	public static void main(String[] args) {
		System.out.println("7�� ����̰ų� 9�� ����� ��");
		for(int i = 1; i < 100; i++)
		{
			if(((i % 7) == 0) || ((i % 9) == 0)) 
			{
				System.out.println(i);
			}
		
			
		}
		System.out.println("7�� ����� 9�� ����� ��");
		for(int i = 1; i < 100; i++ )
		{
			if(((i % 7) == 0) && ((i % 9) == 0)) 
			{
				System.out.println(i);
			}
			
		}
		

	}

}
