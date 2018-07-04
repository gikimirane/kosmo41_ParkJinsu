
import java.util.Random;
import java.util.Scanner;

class Compare
{

	public boolean compare(String[][] arr, String[][] arr2)
	{
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr.length; j++)
			{
				if(arr[i][j] == arr2[i][j])
				{
					continue;
				}
				else
				{
					return false;
				}
			}
		}
		return true;
		
	}
}

public class Puzzle {

	public static void move(String str, String[][] arr)
	{
		String num = null;
		if(str.equalsIgnoreCase("a"))
		{
			for(int i = 0; i < arr.length; i++)
			{
				for(int j = 1; j < arr.length; j++)
				{					
					if(arr[i][j-1].equals("x"))
					{
						num = arr[i][j-1];
						arr[i][j-1] = arr[i][j];
						arr[i][j] = num;
						break;
					}
					
				}
			}
		}
		else if(str.equalsIgnoreCase("d"))
		{
			for(int i = 0; i < arr.length; i++)
			{
				for(int j = 0; j < arr.length-1; j++)
				{
					if(arr[i][j+1].equals("x"))
					{
						num = arr[i][j+1];
						arr[i][j+1] = arr[i][j];
						arr[i][j] = num;
						break;
					}
				}
			}
		}
		else if(str.equalsIgnoreCase("w"))
		{
			for(int i = 1; i < arr.length; i++)
			{
				for(int j = 0; j < arr[i].length; j++)
				{
					if(arr[i-1][j].equals("x"))
					{
						num = arr[i-1][j];
						arr[i-1][j] = arr[i][j];
						arr[i][j] = num;
						i++;
						break;
					}
				}
				
			}
		}
		else if(str.equalsIgnoreCase("s"))
		{
			for(int i = 0; i < arr.length-1; i++)
			{
				for(int j = 0; j < arr[i].length; j++)
				{
					if(arr[i+1][j].equals("x"))
					{
						num = arr[i+1][j];
						arr[i+1][j] = arr[i][j];
						arr[i][j] = num;
						break;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Compare com = new Compare();
		Random ran = new Random();
		String str = new String();
		String[] cha = {"a","d","w","s"};
		String[][] arr = {
				{"1", "2", "3"},
				{"4", "5", "6"},
				{"7", "x", "8"}
		};		
		String[][] arr2 = {
				{"1", "2", "3"},
				{"4", "5", "6"},
				{"7", "8", "x"}
		};
		
		for(int i = 0; i < 300; i++)
		{
			int num = ran.nextInt(4);
			if(cha[num].equalsIgnoreCase("a"))
			{
				Puzzle.move(cha[num], arr);
			}
			else if(cha[num].equalsIgnoreCase("d"))
			{
				Puzzle.move(cha[num], arr);
			}
			else if(cha[num].equalsIgnoreCase("w"))
			{
				Puzzle.move(cha[num], arr);
			}
			else if(cha[num].equalsIgnoreCase("s"))
			{
				Puzzle.move(cha[num], arr);
			}
		}

		for(;;)
		{
			for(int i = 0; i < arr.length; i++)
			{
				for(int j = 0; j < arr.length; j++)
				{
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("[ MOVE ] a:Left , d:Right , w:Up , s:Down");
			System.out.println("[ EXIT ] k:Exit");
			System.out.print("이동키를 입력하세요");
			String key = s.next();
			for(int i = 0; i < 4; i++)
				System.out.println();
			if(key.equalsIgnoreCase("k"))
			{
				System.out.println("게임을 종료합니다");
				break;
			}
			Puzzle.move(key,arr);
		
			if(com.compare(arr, arr2))
			{
				for(int i = 0; i < arr.length; i++)
				{
					for(int j = 0; j < arr.length; j++)
					{
						System.out.print(arr[i][j]+" ");
					}
					System.out.println();
				}
				System.out.println("축하합니다!");
				System.out.println("게임을 종료합니다");
				break;
			}					
		}			
	}
}
