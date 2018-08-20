
public class Quiz13 {

	public static void main(String[] args) {
		/*
		for(int i = 1; i < 10; i++)
		{
			for(int j = 2; j < 10; j++)
			{
				System.out.print(j+"X"+i+"="+(i*j));
				System.out.print('\t');
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------");
		for(int i = 2; i < 10; i++)
		{
			for(int j = 1; j <10; j++)
			{
				System.out.print(i+"X"+j+"="+(i*j));
				System.out.print('\t');
			}
			System.out.println('\n');
		}
		*/
		/*
		int i = 1;
		while(i < 10)
		{
			int j = 2;
			while(j < 10)
			{
				System.out.print(j+"X"+i+"="+(i*j));
				System.out.print('\t');
				j++;
			}
			System.out.println('\n');
			i++;
		}
		System.out.println("==============================================================");
		i = 2;
		while(i < 10)
		{
			int j = 1;
			while(j < 10)
			{
				System.out.print(i+"X"+j+"="+(i*j));
				System.out.print('\t');
				j++;
			}
			System.out.println('\n');
			i++;
		}
		*/
		int j = 1;
		do
		{
			int i = 2;
			do
			{
				
				System.out.print(i+"X"+j+"="+(i*j));
				System.out.print('\t');
				i++;
			}while(i < 10);
			System.out.println('\n');
			j++;
		}while(j < 10);
		System.out.println("========================================================");
		
		int i = 2;
		do
		{
			j = 1;
			do
			{
				System.out.print(i+"X"+j+"="+(i*j));
				System.out.print('\t');
				j++;
			}while(j < 10);
			System.out.println('\n');
			i++;
		}while(i < 10);
	}

}
