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
			int i = (min + max)/2;
			System.out.println("Pick a number between 1 and 100");
			System.out.println("If the number is higher than the guess press h");
			System.out.println("If it is less than the guess press l");
			System.out.println("If my guess is correct press y");
			for(;;)
			{
				System.out.println("Is it " + i + "?");
				cha = s.next();
				if(cha.equals(cha1[0]))
				{
					min = i;
					i = (min + max)/2;
					continue;
				}
				else if(cha.equals(cha1[1]))
				{
					max = i;
					i = (min + max)/2;
					continue;
				}
				else if(cha.equals(cha1[2]))
				{
					System.out.println("good");
					break;
				}
			}
			System.out.println("again? < y / n >");
			cha = s.next();
			if(cha.equals(cha1[2]))
			{
				continue;
			}
			else if(cha.equals(cha1[3]))
			{
				break;
			}
		}

	}

}
