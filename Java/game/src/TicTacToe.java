import java.util.Scanner;

public class TicTacToe {
	public static void puzzle2(int num2, String[][] str1)
	{
		Scanner a = new Scanner(System.in);
		String str = Integer.toString(num2);
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
	public static void puzzle1(int num1, String[][] str1)
	{
		Scanner a = new Scanner(System.in);
		String str = Integer.toString(num1);
		for(int i = 0; i < str1.length; i++)
		{
			for(int j = 0; j < str1[i].length; j++)
			{

				if(str.equals(str1[i][j]))
				{
					str1[i][j] = "X";
					return;
				}
				else if(str1[i][j].equalsIgnoreCase("O") || str1[i][j].equalsIgnoreCase("X"))
				{	
					System.out.println("다시 입력하세요");
					num1 = a.nextInt();
					i = 0;
					break;
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
			for(int j = 0; j < str1.length; j++)
			{
				for(int k = 0; k < str1[j].length; k++)
				{
					System.out.print(str1[j][k]);
				}
				System.out.println();
				
			}
			if(i % 2 ==1)
			{
				System.out.println("Player 1, please enter the number of the square");
				System.out.println("where you want to place your X:");	
				int num1 = s.nextInt();
				TicTacToe.puzzle1(num1, str1);
				if(str1[0][1].equals(str1[0][5])&&(str1[0][1].equals(str1[0][9])))
					{
						System.out.println("Player 1, win");
						break;
					}
				if(str1[0][1].equals(str1[2][1])&&(str1[0][1].equals(str1[4][1])))
				{
					System.out.println("Player 1, win"); break;
				}
				if(str1[0][1].equals(str1[2][5])&&(str1[0][1].equals(str1[4][9])))
				{
					System.out.println("Player 1, win"); break;
				}
				if(str1[2][1].equals(str1[2][5])&&(str1[2][1].equals(str1[2][5])))
				{
					System.out.println("Player 1, win"); break;
				}
				if(str1[4][1].equals(str1[2][5])&&(str1[4][1].equals(str1[0][9])))
				{
					System.out.println("Player 1, win"); break;
				}
				if(str1[4][1].equals(str1[4][5])&&(str1[4][1].equals(str1[4][9])))
				{
					System.out.println("Player 1, win"); break;
				}
			}
			else
			{
				System.out.println("Player 2, please enter the number of the square");
				System.out.println("where you want to place your O:");
				int num2 = s.nextInt();
				TicTacToe.puzzle2(num2, str1);
				if(str1[0][1].equals(str1[2][1])&&(str1[0][1].equals(str1[4][1])))
				{
					System.out.println("Player 1, win"); return;
				}
				if(str1[0][1].equals(str1[2][5])&&(str1[0][1].equals(str1[4][9])))
				{
					System.out.println("Player 1, win"); return;
				}
				if(str1[2][1].equals(str1[2][5])&&(str1[2][1].equals(str1[2][5])))
				{
					System.out.println("Player 1, win"); return;
				}
				if(str1[4][1].equals(str1[2][5])&&(str1[4][1].equals(str1[0][9])))
				{
					System.out.println("Player 1, win"); return;
				}
				if(str1[4][1].equals(str1[4][5])&&(str1[4][1].equals(str1[4][9])))
				{
					System.out.println("Player 1, win"); return;
				}
			}

		}
		System.out.println("비겼습니다.");
		
		
	}

}
