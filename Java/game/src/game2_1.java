import java.util.Scanner;

public class game2_1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String cha = new String();
		String[] cha1 = {"h","l","y","n"};
		for(;;)
		{
			int min = 1;
			int max = 100;
			System.out.println("Pick a number between 1 and 100");
			System.out.println("If the number is higher than the guess press h");
			System.out.println("If it is less than the guess press l");
			System.out.println("If my guess is correct press y");
			for(;;)
			{
				int i = (min + max)/2;	
				System.out.println("Is it " + i + "?");
				cha = s.next();
				if(cha.equalsIgnoreCase("h"))
				{
					min = i;
				}
				else if(cha.equalsIgnoreCase("l"))
				{
					max = i;
				}
				else if(cha.equalsIgnoreCase("y"))
				{
					System.out.println("good");
					break;
				}
			}
			System.out.println("again? < y / n >");
			cha = s.next();
			if(cha.equalsIgnoreCase("y"))
			{
				continue;
			}
			else if(cha.equalsIgnoreCase("n"))
			{
				break;
			}
		}

	}

}
