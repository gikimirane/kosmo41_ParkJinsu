import java.util.Scanner;

public class Game3 {
	static int m = 0;
	public static boolean compare(String[][] str1)
	{
		if((str1[0][1].equals(str1[2][1]))&&(str1[0][1].equals(str1[4][1]))
			||(str1[0][1].equals(str1[2][5]))&&(str1[0][1].equals(str1[4][9]))
			||(str1[2][1].equals(str1[2][5]))&&(str1[2][1].equals(str1[2][9]))
			||(str1[4][1].equals(str1[2][5]))&&(str1[4][1].equals(str1[0][9]))
			||(str1[4][1].equals(str1[4][5]))&&(str1[4][1].equals(str1[4][9]))
			||(str1[0][1].equals(str1[0][5]))&&(str1[0][1].equals(str1[0][9]))
			||(str1[0][9].equals(str1[2][9])&&(str1[0][9].equals(str1[4][9])))
			||(str1[0][5].equals(str1[2][5])&&(str1[0][5]).equals(str1[4][5])))
		{
			for(int j = 0; j < str1.length; j++)
			{
				for(int k = 0; k < str1[j].length; k++)
				{
					System.out.print(str1[j][k]);
				}
				System.out.println();
			}
			return true;
		}
		return false;
	}
	public static void puzzle2(String[][] str1)
	{
		Scanner a = new Scanner(System.in);
		for(;m < 5;)
		{
			for(int j = 0; j < str1.length; j++)
			{
				for(int k = 0; k < str1[j].length; k++)
				{
					System.out.print(str1[j][k]);
				}
				System.out.println();
			}
			System.out.println("Player 2, please enter the number of the square");
			System.out.println("where you want to place your O:");	
			int num1 = a.nextInt();
			String str = Integer.toString(num1);
			for(int i = 0; i < str1.length; i++)
			{
				for(int j = 0; j < str1[i].length; j++)
				{
					if(str.equals(str1[i][j]))
					{
						str1[i][j] = "O";
						return;
					}
						
				}
			}
		}

	}
	public static void puzzle1(String[][] str1)
	{
		Scanner a = new Scanner(System.in);
		for(; m < 5;)
		{
			for(int j = 0; j < str1.length; j++)
			{
				for(int k = 0; k < str1[j].length; k++)
				{
					System.out.print(str1[j][k]);
				}
				System.out.println();
			}
			System.out.println("Player 1, please enter the number of the square");
			System.out.println("where you want to place your X:");	
			int num1 = a.nextInt();
			String str = Integer.toString(num1);
			for(int i = 0; i < str1.length; i++)
			{
				for(int j = 0; j < str1[i].length; j++)
				{
					if(str.equals(str1[i][j]))
					{
						str1[i][j] = "X";
						m++;
						return;
					}
						
				}
			}
		}
	}
	public static void main(String[] args) {
		String[][] str1 = {
				{" ","1"," ","|"," ","2"," ","|"," ","3"},
				{"---","|","---","|","---"},
				{" ","4"," ","|"," ","5"," ","|"," ","6"},
				{"---","|","---","|","---"},
				{" ","7"," ","|"," ","8"," ","|"," ","9"},
		};
		Scanner s = new Scanner(System.in);
		for(int i = 1;i<10;i++)
		{
			Game3.puzzle1(str1);
			if(Game3.compare(str1))
			{	
				System.out.println("Player 1, win"); return;
			}	
			Game3.puzzle2(str1);
			if(Game3.compare(str1))
			{
				System.out.println("Player 2, win"); return;
			}
		}
		for(int j = 0; j < str1.length; j++)
		{
			for(int k = 0; k < str1[j].length; k++)
			{
				System.out.print(str1[j][k]);
			}
			System.out.println();
		}
		System.out.println("비겼습니다.");		
	}

}
