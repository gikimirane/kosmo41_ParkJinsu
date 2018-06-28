import java.util.Random;
import java.util.Scanner;

public class game2 {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner s = new Scanner(System.in);
		String[] cha = {"h","l","y","n"};
		String cha1 = new String();
		int min = 1;
		int max = 100;
		int i = (min + max)/2;
		System.out.println("Pick a number between 1 and 100");
		System.out.println("If the number is higher than the guess press h");
		System.out.println("If it is less than the guess l");
		System.out.println("If my guess is correct press y");
		for(;;)
		{
			System.out.println("Is it "+ i +"?");
			cha1 = s.next();
			if(cha1.equals(cha[0]))
			{
				min = i;
				i = (i + max)/2;
				continue;
			}
			else if(cha1.equals(cha[1]))
			{
				max = i;
				i = (i + min)/2;
				continue;
			}
			else if(cha1.equals(cha[2]))
			{
				System.out.println("good");
				break;
			}
			
		}
	}
}